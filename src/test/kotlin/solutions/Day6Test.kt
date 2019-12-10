package solutions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Day6Test {

    @Test
    fun part1() {
        val testInput: List<Pair<String, String>> = listOf(
            "COM)B",
            "B)C",
            "C)D",
            "D)E",
            "E)F",
            "B)G",
            "G)H",
            "D)I",
            "E)J",
            "J)K",
            "K)L"
        ).map { it.split(')') }.map { Pair(it[0], it[1]) }

        assertEquals(42, Day6().part1(testInput))
    }

    @Test
    fun part1Small() {
        val testInput: List<Pair<String, String>> = listOf(
            "COM)B",
            "B)C"
        ).map { it.split(')') }.map { Pair(it[0], it[1]) }

        assertEquals(3, Day6().part1(testInput))
    }

    @Test
    fun getOrbitTest() {
        val tree = OrbitTree("COM")
        tree.addOrbit("B", "COM")
        tree.addOrbit("C", "B")
        tree.addOrbit("D", "C")

        val d = tree.get("D")
        assertEquals(3, d!!.getDepth())

    }
}