package solutions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day1Test {

    @Test
    fun getFuelRequiredTest() {
//        solutions.day1().part1()
        assertEquals(654,Day1().getFuelRequired(1969))
        assertEquals(216,Day1().getFuelRequired(654))
        assertEquals(70, Day1().getFuelRequired(216))
    }
}