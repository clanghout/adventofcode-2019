package solutions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day2Test {

    @Test
    fun intComputer() {
    }

    @Test
    fun tests() {
        assertEquals(2, Day2().intComputer(intArrayOf(1, 0, 0, 0, 99)))
        assertEquals(2, Day2().intComputer(intArrayOf(2, 3, 0, 3, 99)))
    }
}