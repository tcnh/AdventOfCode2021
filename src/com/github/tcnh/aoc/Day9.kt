package com.github.tcnh.aoc

import java.io.File

val day9inputs = mutableMapOf<Pair<Int, Int>, Int>()

fun main() {
    File("input_day9").readLines()
        .forEachIndexed { y, line ->
            line.split("(?!^)".toRegex()).forEachIndexed { x, height ->
                if (height.isNotEmpty()) day9inputs += Pair(x, y) to height.toInt()
            }
        }

    day9Part1()
    day9Part2()
}

private fun day9Part1() {
    var sumOfRisk = 0
    day9inputs.keys.forEach {
        if (adjacentAreLower(it)) {
            sumOfRisk += day9inputs[it]?.plus(1)!!
        }
    }
    println(sumOfRisk)
}

private fun day9Part2() {
    val basins = mutableListOf<Int>()
    day9inputs.keys.forEach {
        if (adjacentAreLower(it)) {
            basins.add(findBasinSize(it))
        }
    }
    basins.sort()
    println(basins.takeLast(3).reduce { acc, i -> acc * i })
}

fun adjacentAreLower(position: Pair<Int, Int>): Boolean {
    return (adjacentPositions(position).all { day9inputs.getOrDefault(it, Int.MAX_VALUE) > day9inputs[position]!! })
}

fun findBasinSize(position: Pair<Int, Int>, counted: MutableSet<Pair<Int, Int>> = mutableSetOf(position)): Int {
    adjacentPositions(position).filter {
        day9inputs.getOrDefault(it, 9) in (day9inputs[position]!! + 1)..8
    }.forEach {
        counted.add(it)
        findBasinSize(it, counted)
    }
    return counted.size
}

private fun adjacentPositions(position: Pair<Int, Int>): List<Pair<Int, Int>> {
    return listOf(
        position.first + 1 to position.second,
        position.first - 1 to position.second,
        position.first to position.second + 1,
        position.first to position.second - 1
    )
}