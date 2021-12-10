package com.github.tcnh.aoc

import java.io.File

val day10Input = File("input_day10").readLines()
val pairs = listOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')
val errorScores = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
val completionScores = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

fun main() {
    solveDay10()
}

private fun solveDay10() {
    val errors = mutableListOf<Char>()
    val completions = mutableListOf<String>()
    day10Input.forEach line@{ l ->
        with(ArrayDeque<Char>()) {
            l.asSequence().forEach {
                if (pairs.any { p -> p.first == it }) {
                    add(it)
                } else {
                    if (pairs.first { p -> p.second == it }.first == last()) {
                        removeLast()
                    } else {
                        errors.add(it)
                        return@line
                    }
                }
            }
            completions.add(String(reversed().map { pairs.first { p -> p.first == it }.second }.toCharArray()))
        }
    }
    println("Part 1: ${errors.toSet().sumOf { errors.count { e -> e == it } * errorScores[it]!! }}")

    val completionScoreList = completions.map { it.asSequence().fold(0L) { acc, c -> acc * 5 + completionScores[c]!! } }
    println("Part 2: ${completionScoreList.sorted()[completionScoreList.size / 2]}")
}
