package com.github.tcnh.aoc

import java.io.File

fun main() {
    val inputs = File("input_day1").readLines().map { it.toInt() }
    println("Part 1: ${inputs.windowed(2).count { it.last() > it.first() }}")
    println("Part 2: ${inputs.windowed(4).count { it.last() > it.first() }}")
}