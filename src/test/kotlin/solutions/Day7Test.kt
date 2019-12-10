package solutions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day7Test {
    private val isInputZero = intArrayOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9, 11)
    private val isInputZeroImmediate = intArrayOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)


    @Test
    fun systemTest() {
        val exprogram = intArrayOf(3, 15, 3, 16, 1002, 16, 10, 16, 1, 16, 15, 15, 4, 15, 99, 0, 0)
        assertEquals(43210, Day7().part1(exprogram, listOf(listOf(4, 3, 2, 1, 0))))
    }

    @Test
    fun systemTest2() {
        val exprogram = intArrayOf(
            3, 23, 3, 24, 1002, 24, 10, 24, 1002, 23, -1, 23,
            101, 5, 23, 23, 1, 24, 23, 23, 4, 23, 99, 0, 0
        )
        assertEquals(54321, Day7().part1(exprogram, listOf(listOf(0, 1, 2, 3, 4))))
        assertEquals(54321, Day7().part1(exprogram, null))
    }


    @Test
    fun systemTest3() {
        val exProgram = intArrayOf(
            3, 31, 3, 32, 1002, 32, 10, 32, 1001, 31, -2, 31, 1007, 31, 0, 33,
            1002, 33, 7, 33, 1, 33, 31, 31, 1, 32, 31, 31, 4, 31, 99, 0, 0, 0
        )
        assertEquals(65210, Day7().part1(exProgram, listOf(listOf(4, 3, 2, 1, 0))))
        assertEquals(65210, Day7().part1(exProgram, null))
    }

    @Test
    fun intComputerEquals8Imm() {
        val lessThanEightImmediate = intArrayOf(3, 3, 1107, -1, 8, 3, 4, 3, 99)
        assertEquals(0, Day7().intComputer(lessThanEightImmediate, listOf(8)).second)
        assertEquals(0, Day7().intComputer(lessThanEightImmediate, listOf(9)).second)
        assertEquals(1, Day7().intComputer(lessThanEightImmediate, listOf(7)).second)
    }

    @Test
    fun system2Test() {
        val loopProgram = intArrayOf(
            3, 26, 1001, 26, -4, 26, 3, 27, 1002, 27, 2, 27, 1, 27, 26,
            27, 4, 27, 1001, 28, -1, 28, 1005, 28, 6, 99, 0, 0, 5
        )
        assertEquals(139629729, Day7().part2(loopProgram, listOf(listOf(9, 8, 7, 6, 5))))
    }

    @Test
    fun system2Test2() {
        val loopProgram = intArrayOf(
            3, 52, 1001, 52, -5, 52, 3, 53, 1, 52, 56, 54, 1007, 54, 5, 55, 1005, 55, 26, 1001, 54,
            -5, 54, 1105, 1, 12, 1, 53, 54, 53, 1008, 54, 0, 55, 1001, 55, 1, 55, 2, 53, 55, 53, 4,
            53, 1001, 56, -1, 56, 1005, 56, 6, 99, 0, 0, 0, 0, 10
        )
        assertEquals(18216, Day7().part2(loopProgram, listOf(listOf(9, 7, 8, 5, 6))))
    }


    @Test
    fun intComputerEquals8() {
        val equalsEight = intArrayOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(1, Day7().intComputer(equalsEight, listOf(8)).second)
        assertEquals(0, Day7().intComputer(equalsEight, listOf(7)).second)
    }

    @Test
    fun intComputerLess8() {
        val lessThanEight = intArrayOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(0, Day7().intComputer(lessThanEight, listOf(8)).second)
        assertEquals(0, Day7().intComputer(lessThanEight, listOf(9)).second)
        assertEquals(1, Day7().intComputer(lessThanEight, listOf(7)).second)
        assertEquals(1, Day7().intComputer(lessThanEight, listOf(-1)).second)
    }

    @Test
    fun intComputerEquals8Immediate() {
        val equalsEightImmediate = intArrayOf(3, 3, 1108, -1, 8, 3, 4, 3, 99)
        assertEquals(1, Day7().intComputer(equalsEightImmediate, listOf(8)).second)
        assertEquals(0, Day7().intComputer(equalsEightImmediate, listOf(7)).second)
    }

    @Test
    fun intComputerIsZero() {
        assertEquals(1, Day7().intComputer(isInputZero, listOf(1)).second)
    }

    @Test
    fun intComputerIsZero2() {
        assertEquals(1, Day7().intComputer(isInputZero, listOf(5)).second)
    }

    @Test
    fun intComputerIsZero3() {
        assertEquals(0, Day7().intComputer(isInputZero, listOf(0)).second)
    }

    @Test
    fun intComputerIsZero4() {
        assertEquals(1, Day7().intComputer(isInputZero, listOf(111)).second)
    }

    @Test
    fun intComputerIsZero5() {
        assertEquals(1, Day7().intComputer(isInputZero, listOf(-111)).second)

    }

    @Test
    fun intComputerIsZeroImmediate() {
        assertEquals(1, Day7().intComputer(isInputZeroImmediate, listOf(1)).second)
    }

    @Test
    fun intComputerIsZeroImmediate2() {
        assertEquals(0, Day7().intComputer(isInputZeroImmediate, listOf(0)).second)
    }

    @Test
    fun intComputerIsZeroImmediate3() {
        assertEquals(1, Day7().intComputer(isInputZeroImmediate, listOf(-1)).second)
    }

    @Test
    fun intComputerLarger() {
        val compareEight = intArrayOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        assertEquals(999, Day7().intComputer(compareEight, listOf(-777)).second)
        assertEquals(999, Day7().intComputer(compareEight, listOf(-1)).second)
        assertEquals(999, Day7().intComputer(compareEight, listOf(0)).second)
        assertEquals(999, Day7().intComputer(compareEight, listOf(1)).second)
        assertEquals(999, Day7().intComputer(compareEight, listOf(7)).second)
        assertEquals(1000, Day7().intComputer(compareEight, listOf(8)).second)
        assertEquals(1001, Day7().intComputer(compareEight, listOf(9)).second)
        assertEquals(1001, Day7().intComputer(compareEight, listOf(999)).second)
    }

}