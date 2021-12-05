package com.github.tcnh.aoc

import java.io.File
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign

val day5lines = File("input_day5").readLines()
val grid = mutableMapOf<Pair<Int, Int>, Int>()

fun main() {
    val lines = arrayListOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    day5lines.forEach { line ->
        val (start, finish) = line.split(" -> ")
        val (x1, y1) = start.split(",").map { it.toInt() }
        val (x2, y2) = finish.split(",").map { it.toInt() }
        lines.add(Pair(Pair(x1, y1), Pair(x2, y2)))
    }

    lines.forEach { draw(it) }
    println("Part 1: ${grid.count { it.value > 1 }}")

    grid.clear() // reset grid for part 2

    lines.forEach { draw(it, false) }
    println("Part 1: ${grid.count { it.value > 1 }}")
}

fun draw(coords: Pair<Pair<Int, Int>, Pair<Int, Int>>, horizontalOnly: Boolean = true) {
    val x1 = coords.first.first
    val y1 = coords.first.second
    val x2 = coords.second.first
    val y2 = coords.second.second

    val steps = max(abs(x1 - x2), abs(y1 - y2))
    val xDirection = (x1 - x2).sign
    val yDirection = (y1 - y2).sign

    for (i in 0..steps) {
        if (horizontalOnly && xDirection != 0 && yDirection != 0) break
        val hit = Pair(x1 - i * xDirection, y1 - i * yDirection)
        grid[hit] = (grid[hit] ?: 0) + 1
    }
}
