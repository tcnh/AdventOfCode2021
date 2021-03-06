package com.github.tcnh.aoc

import java.io.File

val day8inputs: List<String> = File("input_day8").readLines()

fun main() {
    //Part 1
    val identifiableCounts = arrayOf(2, 4, 3, 7)
    println(day8inputs.sumOf { input ->
        input.substringAfter("| ").split(" ").count { identifiableCounts.contains(it.length) }
    })

    //Part 2
    var p2Result = 0
    day8inputs.forEach {
        val (input, output) = it.split(" | ")
        var result = ""
        output.split(" ").forEach { digit ->
            result += findDigit(digit, decode(input.split(" ")))
        }
        p2Result += result.toInt()
    }
    println(p2Result)
}

fun decode(input: List<String>): Map<Int, Char> {
    val segments = mutableMapOf<Int, Char>()

    val one = input.first { it.length == 2 }.toCharArray().toSet()
    val four = input.first { it.length == 4 }.toCharArray().toSet()
    val seven = input.first { it.length == 3 }.toCharArray().toSet()

    // get all digits of 5 segments. Overlapping 3 segments are 0,3,6
    val fiveSegmentDigits = input.filter { it.length == 5 }.map { str -> str.toCharArray().toSet() }
    val intersects = fiveSegmentDigits[0].intersect(fiveSegmentDigits[1]).intersect(fiveSegmentDigits[2])

    // The one in four is 3
    segments[3] = intersects.intersect(four).first()

    // The one in 7 is 0
    segments[0] = intersects.intersect(seven).first()

    // that leaves one option for 6
    segments[6] = intersects.minus(listOf(segments[0], segments[3]).toSet()).first()!!

    // Remove 0,3,6 from four, remove one, the remaining is 1
    segments[1] = four.minus(segments.values.toSet()).minus(one).first()

    // In the five segment digits, the one that has 1, reveals 5 (now the only unknown)
    segments[5] = fiveSegmentDigits.first { it.contains(segments[1]) }.minus(segments.values.toSet()).first()

    // 2 is found by removing 5 from the segments in one
    segments[2] = one.minus(segments[5]).first()!!

    // Now the remaining segment is 4
    segments[4] = setOf('a', 'b', 'c', 'd', 'e', 'f', 'g').minus(segments.values.toSet()).first()

    return segments
}

fun findDigit(digit: String, cypher: Map<Int, Char>): Int {
    when (digit.length) {
        2 -> return 1
        4 -> return 4
        3 -> return 7
        7 -> return 8
    }

    val ambiguousDigits = linkedMapOf(
        2 to arrayOf(0, 2, 3, 4, 6),
        3 to arrayOf(0, 2, 3, 5, 6),
        5 to arrayOf(0, 1, 3, 5, 6),
        6 to arrayOf(0, 1, 3, 4, 5, 6),
        9 to arrayOf(0, 1, 2, 3, 5, 6),
        0 to arrayOf(0, 1, 2, 4, 5, 6)
    )

    val signals = mutableListOf<Int>()
    digit.forEach { c -> signals += cypher.filterValues { it == c }.keys.first() }
    signals.sort()
    return ambiguousDigits.filterValues { it.contentEquals(signals.toTypedArray()) }.keys.first()
}