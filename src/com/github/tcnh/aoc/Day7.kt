package com.github.tcnh.aoc

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.floor

val day7input: List<Int> = File("input_day7").readLines()[0].split(",").map { it.toInt() }

fun main() {
    //Part 1:
    val median = day7input.sorted()[(day7input.size + 1) / 2]
    var part1Consumption = 0
    day7input.forEach { i -> part1Consumption += (i - median).absoluteValue }
    println(part1Consumption)

    //Part 2
    val average = floor(day7input.average())
    var part2Consumption = 0
    day7input.forEach { i -> part2Consumption += ((((i - average).absoluteValue + 1) / 2) * (i - average).absoluteValue).toInt() }
    println(part2Consumption)
}

