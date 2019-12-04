package solutions

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day4Test {

    @Test
    fun isValidPassword() {
        assertTrue(Day4().isValidPassword(111111))
        assertFalse(Day4().isValidPassword(223450))
        assertFalse(Day4().isValidPassword(123789))
        assertTrue(Day4().isValidPassword(112345))
    }

    @Test
    fun isValidPassword2() {
        assertTrue(Day4().isValidPassword2(123455))
        assertTrue(Day4().isValidPassword2(1123455))
        assertTrue(Day4().isValidPassword2(123345))

        assertFalse(Day4().isValidPassword2(12345))
        assertFalse(Day4().isValidPassword2(123544))

        assertFalse(Day4().isValidPassword2(223450))


        assertFalse(Day4().isValidPassword2(123444))

        assertTrue(Day4().isValidPassword2(112233))

        assertTrue(Day4().isValidPassword2(111122))

        assertFalse(Day4().isValidPassword2(111123))
        assertTrue(Day4().isValidPassword2(111233))

        assertTrue(Day4().isValidPassword2(667999))


        // bug case found
        // assertTrue(Day4().isValidPassword2Old(667999))
    }
}