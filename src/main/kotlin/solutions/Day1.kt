package solutions

import Day
import java.io.File
import java.lang.Integer.parseInt

class Day1 : Day {
    fun part1(): Int {
        return File("src/main/resources/input1").readLines().map { parseInt(it) / 3 - 2 }.sum()
    }


    override fun run() {

        println("Result of 100756 = " + part2(100756))

        println("part2res"+ File("src/main/resources/input1.txt").readLines().map {
            part2(
                parseInt(it) / 3 - 2
            )
        }.sum())
    }


    fun part2(initFuel: Int): Int {

        var additionalFuel = getFuelRequired(initFuel)

        var result = initFuel

        while (additionalFuel > 0) {
            println("""adding $additionalFuel to $result""")
            result += additionalFuel
            additionalFuel = getFuelRequired(additionalFuel)
        }
        return result
    }

    fun getFuelRequired(mass: Int): Int {
        return mass / 3 - 2
    }
}