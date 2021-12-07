package com.github.tcnh.aoc

import java.io.File
import kotlin.math.abs
import kotlin.math.floor

val day7input: List<Int> = File("input_day7").readLines()[0].split(",").map { it.toInt() }

fun main() {
    //Part 1:
    val median = day7input.sorted()[(day7input.size + 1) / 2]
    var part1Consumption = 0
    day7input.forEach { part1Consumption += abs((it - median)) }
    println(part1Consumption)

    //Part 2
    val average = floor(day7input.average()).toInt()
    var part2Consumption = 0
    day7input.forEach { part2Consumption += (((abs(it - average) + 1) / 2) * abs(it - average)) }
    println(part2Consumption)
}

