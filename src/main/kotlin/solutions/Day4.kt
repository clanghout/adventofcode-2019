package solutions

import Day

class Day4 : Day {
    override fun run() {
        val input = Pair(152085, 670283)

        println(part1(input.first, input.second))

        println(part2(input.first, input.second))

        println("-------")
        for(i in input.first..input.second){
            if(isValidPassword2(i) != isValidPassword2Old(i)){
                println(i)
            }
        }
    }

    fun part1(start: Int, end: Int): Int {
        assert(start < end)

        var possiblePasswords = 0

        for (i in start..end) {
            if (isValidPassword(i)) {
                possiblePasswords++
            }
        }
        return possiblePasswords
    }

    fun part2(start: Int, end: Int): Int {
        assert(start < end)

        var possiblePasswords = 0

        for (i in start..end) {
            if (isValidPassword2(i)) {
                possiblePasswords++
            }
        }
        return possiblePasswords
    }

    fun isValidPassword(i: Int): Boolean {
        var hasdouble = false;
        val charArray = i.toString().toCharArray()
        val validChars: List<Boolean> = charArray.mapIndexed { index, c ->
            var previous = '0'
            var ret = true
            if (index > 0) {
                previous = charArray.elementAt(index - 1)
            }
            if (c < previous) {
                ret = false
            }
            if (c == previous) {
                hasdouble = true
            }
            ret
        }
        return validChars.reduce { acc, nothing -> acc && nothing } && hasdouble
    }

    fun isValidPassword2Old(i: Int): Boolean {
        var hasdouble = false
        var isDoublePair = false
        val charArray = i.toString().toCharArray()
        val validChars: List<Boolean> = charArray.mapIndexed { index, c ->
            var previous = '0'
            var ret = true
            if (index > 0) {
                previous = charArray.elementAt(index - 1)
            }
            if (c < previous) {
                isDoublePair = false
                ret = false
            } else if (c == previous) {
                // check if we are actual double
                if ((index > 2 && charArray[index - 2] != c) || (index < charArray.lastIndex - 1 && charArray[index + 1] != c)) {
                    hasdouble = true
                }

                if (isDoublePair) {
                    // BUG: only remove observation of current double, not every double seen before
                    hasdouble = false
                } else {
                    isDoublePair = true
                }
            } else {
                isDoublePair = false
            }
            ret
        }
        return validChars.reduce { acc, nothing -> acc && nothing } && hasdouble
    }

    fun isValidPassword2(i: Int): Boolean {
        val charArray = i.toString().map(Character::getNumericValue)
        return charArray.zipWithNext().filter { (a, b) -> a == b }.groupBy { it.first }.any { it.value.size == 1 } && charArray == charArray.sorted()
    }
}