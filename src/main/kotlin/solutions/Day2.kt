package solutions

import Day
import java.io.File
import java.lang.Integer.parseInt

class Day2 : Day {

    override fun run() {
        val input: IntArray = File("src/main/resources/input2").readText().split(',').map { parseInt(it) }.toIntArray()

        input[1] = 12
        input[2] = 2
        println("""part 1: ${intComputer(input.copyOf())}""")


        // Part 2
        for (i in 0..99) {
            for (j in 0..99) {
                input[1] = i
                input[2] = j
                if (intComputer(input.copyOf()) == 19690720) {
                    println("""part 2: $i + $j""")
                }
            }
        }
    }

    fun intComputer(input: IntArray): Int {
        var index = 0;

        loop@ while (index < input.lastIndex) {
            when {
                input[index] == 1 -> {
                    input[input[index + 3]] = input[input[index + 1]] + input[input[index + 2]]
                    index += 4;
                }
                input[index] == 2 -> {
                    input[input[index + 3]] = input[input[index + 1]] * input[input[index + 2]]
                    index += 4;
                }
                input[index] == 99 -> {
                    break@loop
                }
                else -> {
                    print("""incorrect input... ${input[index]} at position $index""")
                    break@loop
                }
            }

        }

        return input[0]
    }

}
