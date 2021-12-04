package com.github.tcnh.aoc

import java.io.File

val inputs = File("input_day2").readLines()

fun main() {
    println("Part 1: ${part1(inputs)}")
    println("Part 2: ${part2(inputs)}")
}

fun part1(inputs: List<String>): Int {
    var pos = 0
    var depth = 0

    inputs.forEach {
        val (instr, amount) = it.split(" ")
        when (instr) {
            "forward" -> pos += amount.toInt()
            "down" -> depth += amount.toInt()
            "up" -> depth -= amount.toInt()
        }
    }
    return pos * depth
}

fun part2(inputs: List<String>): Int {
    var pos = 0
    var aim = 0
    var depth = 0

    inputs.forEach {
        val (instr, amount) = it.split(" ")
        when (instr) {
            "forward" -> {
                pos += amount.toInt()
                depth += aim * amount.toInt()
            }
            "down" -> aim += amount.toInt()
            "up" -> aim -= amount.toInt()
        }
    }
    return pos * depth
}