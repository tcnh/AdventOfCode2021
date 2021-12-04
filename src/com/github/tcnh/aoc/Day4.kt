package com.github.tcnh.aoc

import java.io.File

val day4lines = File("input_day4").readLines()
val numbers = day4lines[0].split(",").map { it.toInt() }
val boards = ArrayList<BingoBoard>()

fun main() {
    part1()
    part2()
}

fun part1() {
    initializeBoards()
    numbers.forEach {
        boards.forEach { board ->
            board.play(it)
            if (board.finalScore >= 0) {
                println("Part 1 - Winner! Score: ${board.finalScore}")
                return
            }
        }
    }
}

private fun part2() {
    initializeBoards()
    var lastWinnerScore = -1
    numbers.forEach {
        boards.forEach { board ->
            if (board.finalScore < 0) {
                board.play(it)
                if (board.finalScore >= 0) {
                    lastWinnerScore = board.finalScore
                }
            }
        }
    }
    println("Part 2 - Last winner: $lastWinnerScore")
}

private fun initializeBoards() {
    boards.clear()
    for (i in 1 until day4lines.size step 6) {
        val rows = arrayListOf<java.util.ArrayList<Int>>()
        (1..5).asSequence().map { row ->
                day4lines[i + row].trim()
                    .split("\\s+".toRegex())
                    .map { it.toInt() }
            }.forEach { rows.add(it as java.util.ArrayList<Int>) }
        boards += BingoBoard(rows)
    }
}

class BingoBoard(private val rows: ArrayList<ArrayList<Int>>) {
    private val cols = arrayListOf<java.util.ArrayList<Int>>()
    var finalScore = -1

    init {
        for (i in 0..4) {
            val col = arrayListOf<Int>()
            rows.forEach { col.add(it[i]) }
            cols.add(col)
        }
    }

    fun play(number: Int) {
        rows.forEach { it.remove(number) }
        cols.forEach { it.remove(number) }
        if (rows.any { it.isEmpty() } or cols.any { it.isEmpty() }) {
            finalScore = rows.sumOf { it.sum() } * number
        }
    }
}