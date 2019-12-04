package solutions

import main.com.aoc19.Day3
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day3Test {
    @Test
    fun testInput() {
        val testInput = listOf(
            listOf(
                "R75", "D30", "R83", "U83", "L12", "D49", "R71", "U7", "L72"
            ), listOf(
                "U62", "R66", "U55", "R34", "D71", "R55", "D58", "R83"
            )
        )

        println(Day3().part1(testInput))
    }
}