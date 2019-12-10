package solutions

import Day
import java.io.File

class Day7 : Day {
    override fun run() {
        val input: IntArray = File("src/main/resources/input7")
            .readText().split(',').map { Integer.parseInt(it) }.toIntArray()


        println(part2(input, null))
    }

    fun <T> permute(input: List<T>): List<List<T>> {
        if (input.size == 1) return listOf(input)
        val perms = mutableListOf<List<T>>()
        val toInsert = input[0]
        for (perm in permute(input.drop(1))) {
            for (i in 0..perm.size) {
                val newPerm = perm.toMutableList()
                newPerm.add(i, toInsert)
                perms.add(newPerm)
            }
        }
        return perms
    }


    fun part1(program: IntArray, sequence: List<List<Int>>?): Int {
        val nrOfAmplifiers = 5

        var maxResult = 0

        val perms: List<List<Int>> = sequence ?: permute((0..4).toList())

        perms.forEach {
            var input = 0
            for (amp in 0 until nrOfAmplifiers) {
                input = runProgram(program.copyOf(), input, it[amp])
                println("""new input = $input""")
            }
            if (input > maxResult) {
                maxResult = input
            }
        }
        return maxResult
    }

    fun part2(program: IntArray, sequence: List<List<Int>>?): Int {
        val amplifiers = mutableListOf(
            program.copyOf(),
            program.copyOf(),
            program.copyOf(),
            program.copyOf(),
            program.copyOf()
        )

        val perms: List<List<Int>> = sequence ?: permute((5..9).toList())

        perms.forEach {
            var input = 0
            var loops = 0
            while (loops < 30) {
                loops++
                for (amp in 0 until amplifiers.size) {
                    val runProgram2 = runProgram2(amplifiers[amp], input, it[amp])
                    if (runProgram2.third) {
                        input = runProgram2.second
                        amplifiers[amp] = runProgram2.first
                        println("""new input = $input""")
                    } else {
                        return input
                    }
                }
                println("""loop $loops done""")
            }

        }
        return -1
    }

    private fun runProgram(program: IntArray, input: Int, sequence: Int): Int {
        return intComputer(program, listOf(sequence, input)).second
    }

    private fun runProgram2(program: IntArray, input: Int, sequence: Int): Triple<IntArray, Int, Boolean> {
        return intComputer(program, listOf(sequence, input))
    }

    fun intComputer(program: IntArray, userInput: List<Int>): Triple<IntArray, Int, Boolean> {
        var index = 0
        val register = mutableListOf<Int>(userInput[0])
        if (userInput.size > 1) {
            register.add(userInput[1])
        }
        else{
            register.add(userInput[0])
        }
        var readFirstInput = false

        var loops = 0

        loop@ while (index < program.lastIndex && loops < 5000) {
            loops++

            when (program[index] % 100) {
                1 -> {
                    program[program[index + 3]] =
                        getParam(program, index, 1) +
                                getParam(program, index, 2)
                    index += 4
                }
                2 -> {
                    program[program[index + 3]] = getParam(program, index, 1) * getParam(program, index, 2)
                    index += 4
                }
                3 -> {
                    if (readFirstInput) {
                        program[program[index + 1]] = register[1]
                    } else {
                        program[program[index + 1]] = register[0]
                        readFirstInput = true
                    }
                    index += 2
                }
                4 -> {
                    register[1] = getParam(program, index, 1)
                    index += 2
                }
                5 -> { // jump if true
                    if (getParam(program, index, 1) != 0) {
                        index = getParam(program, index, 2)
                    } else {
                        index += 3
                    }
                }
                6 -> { // jump if false
                    val param = getParam(program, index, 1)
                    if (param == 0) {
                        index = getParam(program, index, 2)
                    } else {
                        index += 3
                    }
                }
                7 -> { // less than
                    if (getParam(program, index, 1) < getParam(program, index, 2)) {
                        program[program[index + 3]] = 1
                    } else {
                        program[program[index + 3]] = 0
                    }
                    index += 4
                }
                8 -> { // equals
                    if (getParam(program, index, 1) == getParam(program, index, 2)) {
                        program[program[index + 3]] = 1
                    } else {
                        program[program[index + 3]] = 0
                    }
                    index += 4
                }
                99 -> {
                    break@loop
                }
                else -> {
                    println("""incorrect input... ${program[index]} at position $index""")
                    break@loop
                }
            }

        }
        println("""$loops were needed""")

        return Triple(program, register[1], loops <= 4500)
    }

    private fun getParam(input: IntArray, index: Int, paramNr: Int) =
        getParamValue(input, index + paramNr, Day5().isParamImmediate(input[index], paramNr))

    private fun getParamValue(input: IntArray, index: Int, isImmediate: Boolean): Int {
        if (isImmediate) {
            return input[index]
        }
        return input[input[index]]
    }
}