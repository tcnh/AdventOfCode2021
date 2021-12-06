package com.github.tcnh.aoc

import java.io.File

val day6input = File("input_day6").readLines()[0]
val schoolOfLanternFish = mutableListOf<LanternFish>()

fun main() {
    for (timer in day6input.split(",").map { it.toInt() }) {
        schoolOfLanternFish += LanternFish(timer)
    }
    //Part1
    println(growFishes(80))

    //Part2 .. Goes out of memory when keeping track of all the little fishes. Just count the process
    val fishArray = LongArray(9)

    day6input.split(",").map { it.toInt() }.forEach {
        fishArray[it] = fishArray[it].plus(1)
    }

    for (day in 1..256) {
        val spawningFishes = fishArray[0]
        fishArray[0] = fishArray[1]
        fishArray[1] = fishArray[2]
        fishArray[2] = fishArray[3]
        fishArray[3] = fishArray[4]
        fishArray[4] = fishArray[5]
        fishArray[5] = fishArray[6]
        fishArray[6] = fishArray[7] + spawningFishes
        fishArray[7] = fishArray[8]
        fishArray[8] = spawningFishes
    }

    println(fishArray.sum())

}

fun growFishes(days: Int): Double {
    for (day in 1..days) {
        val todaysSchool = schoolOfLanternFish.toMutableList()
        todaysSchool.forEach { it.nextDay() }
    }
    return schoolOfLanternFish.size.toDouble()
}

class LanternFish(var timer: Int = 8) {
    fun nextDay() {
        timer = if (timer - 1 < 0) {
            schoolOfLanternFish += LanternFish()
            6
        } else {
            timer - 1
        }
    }
}