package main.com.aoc19

import Day
import java.io.File
import java.lang.Integer.parseInt

class Day3 : Day {

    override fun run() {
        val input = File("src/main/resources/input3.txt").readLines().map { it.split(',') }

        println(part1(listOf(listOf("R8", "U5", "L5", "D3"), listOf("U7", "R6", "D4", "L4"))))

        println(part1(input))


        println(part2(input))
    }

    fun part1(input: List<List<String>>): Int {
        val firstCoords: HashMap<Int, MutableSet<Int>> = HashMap(10000)

        var curX = 0
        var curY = 0

        input[0].forEach {
            val direction = it.substring(0, 1)
            val steps = parseInt(it.substring(1))
            when (direction) {
                "U" -> {
                    for (i in 1..steps) {
                        curY++
                        addToMap(firstCoords, curX, curY)
                    }
                }
                "D" -> {
                    for (i in 1..steps) {
                        curY--
                        addToMap(firstCoords, curX, curY)
                    }
                }
                "R" -> {
                    for (i in 1..steps) {
                        curX++
                        addToMap(firstCoords, curX, curY)
                    }
                }
                "L" -> {
                    for (i in 1..steps) {
                        curX--
                        addToMap(firstCoords, curX, curY)
                    }
                }
            }
        }

        curX = 0
        curY = 0

        val crossings: HashMap<Int, Int> = HashMap(10)

        input[1].forEach {

            val direction = it.substring(0, 1)
            val steps = parseInt(it.substring(1))
            when (direction) {
                "U" -> {
                    for (i in 1..steps) {
                        curY++
                        when (firstCoords[curX]?.contains(curY)) {
                            true -> {
                                crossings[curX] = curY
                            }
                        }
                    }
                }
                "D" -> {
                    for (i in 1..steps) {
                        curY--
                        when (firstCoords[curX]?.contains(curY)) {
                            true -> {
                                crossings[curX] = curY
                            }
                        }
                    }
                }
                "R" -> {
                    for (i in 1..steps) {
                        curX++
                        when (firstCoords[curX]?.contains(curY)) {
                            true -> {
                                crossings[curX] = curY
                            }
                        }
                    }
                }
                "L" -> {
                    for (i in 1..steps) {
                        curX--
                        when (firstCoords[curX]?.contains(curY)) {
                            true -> {
                                crossings[curX] = curY
                            }
                        }
                    }
                }
            }
        }


        val sorted = crossings.map { Math.abs(it.key) + Math.abs(it.value) }.sorted()

        return sorted[0]
    }

    fun part2(input: List<List<String>>): Int {
        val firstCoords: HashMap<Int, MutableSet<Pair<Int, Int>>> = HashMap(10000)

        var curX = 0
        var curY = 0
        var curLen = 0;

        input[0].forEach {
            val direction = it.substring(0, 1)
            val steps = parseInt(it.substring(1))
            when (direction) {
                "U" -> {
                    for (i in 1..steps) {
                        curY++
                        curLen++
                        addToMap2(firstCoords, curX, Pair(curY, curLen))
                    }
                }
                "D" -> {
                    for (i in 1..steps) {
                        curY--
                        curLen++
                        addToMap2(firstCoords, curX, Pair(curY, curLen))
                    }
                }
                "R" -> {
                    for (i in 1..steps) {
                        curX++
                        curLen++
                        addToMap2(firstCoords, curX, Pair(curY, curLen))
                    }
                }
                "L" -> {
                    for (i in 1..steps) {
                        curX--
                        curLen++
                        addToMap2(firstCoords, curX, Pair(curY, curLen))
                    }
                }
            }
        }

        curX = 0
        curY = 0
        curLen = 0

        var minCrossing = 10000

        input[1].forEach {

            val direction = it.substring(0, 1)
            val steps = parseInt(it.substring(1))
            when (direction) {
                "U" -> {
                    for (i in 1..steps) {
                        curY++
                        curLen++
                        minCrossing = findCrossing(firstCoords, curX, curY, curLen, minCrossing)
                    }
                }
                "D" -> {
                    for (i in 1..steps) {
                        curY--
                        curLen++
                        minCrossing = findCrossing(firstCoords, curX, curY, curLen, minCrossing)
                    }
                }
                "R" -> {
                    for (i in 1..steps) {
                        curX++
                        curLen++
                        minCrossing = findCrossing(firstCoords, curX, curY, curLen, minCrossing)
                    }
                }
                "L" -> {
                    for (i in 1..steps) {
                        curX--
                        curLen++
                        minCrossing = findCrossing(firstCoords, curX, curY, curLen, minCrossing)
                    }
                }
            }
        }


        return minCrossing
    }

    private fun findCrossing(
        firstCoords: HashMap<Int, MutableSet<Pair<Int, Int>>>,
        curX: Int,
        curY: Int,
        curLen: Int,
        minCrossing: Int
    ): Int {
        val filter = firstCoords[curX]?.filter { pair -> pair.first == curY }
        when (filter.isNullOrEmpty()) {
            false -> {
                val curDist = filter[0].second + curLen
                if (curDist < minCrossing) {
                    return curDist
                }
            }
        }
        return minCrossing
    }

    private fun addToMap(
        firstCoords: HashMap<Int, MutableSet<Int>>,
        curX: Int,
        curY: Int
    ) {
        if (firstCoords.containsKey(curX)) {
            firstCoords[curX]!!.add(curY)
        } else {
            firstCoords[curX] = mutableSetOf(curY)
        }
    }

    private fun addToMap2(
        firstCoords: HashMap<Int, MutableSet<Pair<Int, Int>>>,
        curX: Int,
        curY: Pair<Int, Int>
    ) {
        if (firstCoords.containsKey(curX)) {
            firstCoords[curX]!!.add(curY)
        } else {
            firstCoords[curX] = mutableSetOf(curY)
        }
    }
}