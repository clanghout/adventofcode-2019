package solutions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day5Test {
    private val isInputZero = intArrayOf(3, 12, 6, 12, 15, 1, 13, 14, 13, 4, 13, 99, -1, 0, 1, 9, 11)
    private val isInputZeroImmediate = intArrayOf(3, 3, 1105, -1, 9, 1101, 0, 0, 12, 4, 12, 99, 1)

    @Test
    fun intComputerEquals8() {
        val equalsEight = intArrayOf(3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(1, Day5().intComputer(equalsEight, 8))
        assertEquals(0, Day5().intComputer(equalsEight, 7))
    }

    @Test
    fun intComputerLess8() {
        val lessThanEight = intArrayOf(3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8)
        assertEquals(0, Day5().intComputer(lessThanEight, 8))
        assertEquals(0, Day5().intComputer(lessThanEight, 9))
        assertEquals(1, Day5().intComputer(lessThanEight, 7))
        assertEquals(1, Day5().intComputer(lessThanEight, -1))
    }

    @Test
    fun intComputerEquals8Immediate() {
        val equalsEightImmediate = intArrayOf(3, 3, 1108, -1, 8, 3, 4, 3, 99)
        assertEquals(1, Day5().intComputer(equalsEightImmediate, 8))
        assertEquals(0, Day5().intComputer(equalsEightImmediate, 7))
    }

    @Test
    fun intComputerEquals8Imm() {
        val lessThanEightImmediate = intArrayOf(3, 3, 1107, -1, 8, 3, 4, 3, 99)
        assertEquals(0, Day5().intComputer(lessThanEightImmediate, 8))
        assertEquals(0, Day5().intComputer(lessThanEightImmediate, 9))
        assertEquals(1, Day5().intComputer(lessThanEightImmediate, 7))
    }

    @Test
    fun intComputerIsZero() {
        assertEquals(1, Day5().intComputer(isInputZero, 1))
    }

    @Test
    fun intComputerIsZero2() {
        assertEquals(1, Day5().intComputer(isInputZero, 5))
    }

    @Test
    fun intComputerIsZero3() {
        assertEquals(0, Day5().intComputer(isInputZero, 0))
    }

    @Test
    fun intComputerIsZero4() {
        assertEquals(1, Day5().intComputer(isInputZero, 111))
    }

    @Test
    fun intComputerIsZero5() {
        assertEquals(1, Day5().intComputer(isInputZero, -111))

    }

    @Test
    fun intComputerIsZeroImmediate() {
        assertEquals(1, Day5().intComputer(isInputZeroImmediate, 1))
    }

    @Test
    fun intComputerIsZeroImmediate2() {
        assertEquals(0, Day5().intComputer(isInputZeroImmediate, 0))
    }

    @Test
    fun intComputerIsZeroImmediate3() {
        assertEquals(1, Day5().intComputer(isInputZeroImmediate, -1))
    }

    @Test
    fun intComputerLarger() {
        val compareEight = intArrayOf(
            3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
            1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
            999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99
        )
        assertEquals(999, Day5().intComputer(compareEight, -777))
        assertEquals(999, Day5().intComputer(compareEight, -1))
        assertEquals(999, Day5().intComputer(compareEight, 0))
        assertEquals(999, Day5().intComputer(compareEight, 1))
        assertEquals(999, Day5().intComputer(compareEight, 7))
        assertEquals(1000, Day5().intComputer(compareEight, 8))
        assertEquals(1001, Day5().intComputer(compareEight, 9))
        assertEquals(1001, Day5().intComputer(compareEight, 999))
    }

    @Test
    fun isParamImmediate() {
        assertFalse(Day5().isParamImmediate(1002, 1))
        assertTrue(Day5().isParamImmediate(1002, 2))
        assertFalse(Day5().isParamImmediate(1002, 3))
        assertTrue(Day5().isParamImmediate(1101, 2))
        assertTrue(Day5().isParamImmediate(1101, 1))
    }

    @Test
    fun getParam() {
        assertEquals(100, Day5().getParam(intArrayOf(1101, 100, -1, 4, 0), 0, 1))
        assertEquals(-1, Day5().getParam(intArrayOf(1101, 100, -1, 4, 0), 0, 2))
    }

}