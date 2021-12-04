package com.github.tcnh.aoc

import java.io.File
import kotlin.math.abs

val day3inputs = File("input_day3").readLines()

fun main() {
    //Part 1:
    var gammaBinary = ""

    for (i in 0 until day3inputs[0].length) {
        val bits = mutableListOf<Int>()
        day3inputs.forEach { input -> bits.add(input.substring(i, i + 1).toInt()) }
        gammaBinary += when {
            oneMostCommon(bits) -> 1
            else -> 0
        }
    }

    var epsilonBinary = ""
    gammaBinary.toCharArray().forEach { epsilonBinary += abs(it.toString().toInt() - 1) }

    val gammaRate = Integer.parseInt(gammaBinary, 2)
    val epsilonRate = Integer.parseInt(epsilonBinary, 2)

    println("Part 1: ${gammaRate * epsilonRate}")

    //Part 2:
    val oxBin = filterByCommonness(day3inputs, 0, true)
    val co2Bin = filterByCommonness(day3inputs, 0, false)

    println("Part 2: ${Integer.parseInt(oxBin, 2) * Integer.parseInt(co2Bin, 2)}")
}

fun oneMostCommon(bits: List<Int>): Boolean = bits.average() >= 0.5

fun filterByCommonness(list: List<String>, pos: Int, mostCommon: Boolean): String {
    val bits = mutableListOf<Int>()
    list.forEach { input -> bits.add(input.substring(pos, pos + 1).toInt()) }

    val filtered = list.filter {
        it[pos] == when {
            mostCommon -> when {
                oneMostCommon(bits) -> '1'
                else -> '0'
            }
            else -> when {
                !oneMostCommon(bits) -> '1'
                else -> '0'
            }
        }
    }

    return if (filtered.size > 1) {
        filterByCommonness(filtered, pos + 1, mostCommon)
    } else filtered[0]
}
