package solutions

import Day
import java.io.File
import kotlin.math.pow

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class Day5 : Day {
    override fun run() {
        val input: IntArray = File("src/main/resources/input5")
            .readText().split(',').map { Integer.parseInt(it) }.toIntArray()
//        println(intComputer(input, 1))
        println(intComputer(input, 5))
    }

    fun part1() {

    }

    fun intComputer(input: IntArray, userInput: Int): Int {
        var index = 0
        var register = userInput

        loop@ while (index < input.lastIndex) {


            when (input[index] % 100) {
                1 -> {
                    input[input[index + 3]] =
                        getParam(input, index, 1) +
                                getParam(input, index, 2)
                    index += 4;
                }
                2 -> {
                    input[input[index + 3]] = getParam(input, index , 1) * getParam(input, index, 2)
                    index += 4;
                }
                3 -> {
                    input[input[index+1]] = register
                    index += 2
                }
                4 -> {
                    register = getParam(input, index, 1)
                    index += 2
                }
                5 -> { // jump if true
                    if (getParam(input, index, 1) != 0) {
                        index = getParam(input, index, 2)
                    }
                    else {
                        index += 3
                    }
                }
                6 -> { // jump if false
                    val param = getParam(input, index, 1)
                    if (param == 0) {
                        index = getParam(input, index, 2)
                    }
                    else {
                        index += 3
                    }
                }
                7 -> { // less than
                    if(getParam(input, index, 1) < getParam(input, index, 2)){
                        input[input[index + 3]] = 1
                    }
                    else{
                        input[input[index + 3]] = 0
                    }
                    index += 4
                }
                8-> { // equals
                if(getParam(input, index, 1) == getParam(input, index, 2)){
                    input[input[index + 3]] = 1
                }
                else{
                    input[input[index + 3]] = 0
                }
                index += 4
            }
                99 -> {
                    break@loop
                }
                else -> {
                    print("""incorrect input... ${input[index]} at position $index""")
                    break@loop
                }
            }

        }

        return register
    }

    fun getParam(input: IntArray, index: Int, paramNr: Int) =
        getParamValue(input, index + paramNr, isParamImmediate(input[index], paramNr))

    fun isParamImmediate(i: Int, position: Int): Boolean {
        if (i < 100) {
            return false
        }

        val x = position.toDouble() + 2
        val thirdNumber: Int = ((i % (10.0.pow(x)) - i % 10.0.pow(x - 1)) / 10.0.pow(x - 1)).toInt()
        if (thirdNumber == 1) {
            return true
        }
        return false
    }

    fun getParamValue(input: IntArray, index: Int, isImmediate: Boolean): Int {
        if (isImmediate) {
            return input[index]
        }
        return input[input[index]]
    }
}