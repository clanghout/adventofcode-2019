package solutions

import Day
import java.io.File
import java.lang.Integer.parseInt

class Day8 : Day {
    override fun run() {
        val input: List<Int> =
            File("src/main/resources/input8").readText().split("").filter { it.length == 1 }.map { parseInt(it) }
//        println(part1(input))
        part2(input)
    }

    fun part1(input: List<Int>): Int {
        var minLayer = input.withIndex().groupBy { it.index / (25 * 6) }.map { it.value.map { it.value } }
            .map { row -> row.groupBy { it }.map { Pair(it.key, it.value.size) } }
            .minBy { it.find { pair -> pair.first == 0 }!!.second }
        return 129 * 12
    }

    fun part2(input: List<Int>) {
        // 150 layers
        val imageLayer = input.withIndex().groupBy { it.index % (25 * 6) }.map {
            it.value.map { it.value }.reduce { acc, i ->
                var res: Int = 2
                if (acc == 2) {
                    res = i
                } else {
                    res = acc
                }
                res
            }
        }.withIndex().groupBy { it.index / 25 }.map { it.value.map { it.value } }.map {
            it.map {
                var res = "⬜"
                if (it == 2 || it == 0) { // transparent or black
                    res = "⬛"
                }
                res
            }
        }

        printAsImage(imageLayer)

    }

    fun printAsImage(input: List<List<String>>) {
        input.forEach{ println(it.reduce{ acc, i -> acc + i})}
    }
}