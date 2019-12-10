package solutions

import Day
import java.io.File

class Day6 : Day {

    override fun run() {
        val input: List<Pair<String, String>> =
            File("src/main/resources/input6").readLines().filter{it.isNotBlank()}.map { it.split(')') }.map { Pair(it[0], it[1]) }
        println(part2(input))

        // 491 too high
    }

    fun part1(input: List<Pair<String, String>>): Int {
        // Map with name and distance to root
        val orbitTree = OrbitTree("COM")
        fillOrbitTree(input, orbitTree)
        return input.map { orbitTree.get(it.first)?.getDepth()?:0 }.sum()
    }

    fun part2(input: List<Pair<String, String>>): Int {
        val orbitTree = OrbitTree("COM")
        fillOrbitTree(input, orbitTree)

        val youPath = getPathToRoot("YOU", orbitTree).reversed()
        val sanPath = getPathToRoot("SAN", orbitTree).reversed()

        val maxLength = Math.max(youPath.size, sanPath.size)
        var totalLength = youPath.size + sanPath.size

        for(i in 0 until maxLength) {
            if(youPath[i] == sanPath[i]){
                totalLength-=2
            }
            else{
                return totalLength
            }
        }
        return totalLength
    }

    private fun getPathToRoot(key: String, orbitTree: OrbitTree): List<String> {
        var get = orbitTree.get(key)
        val res = mutableListOf<String>()
        while(get?.parent != null) {
           res.add(get.parent!!.key)
            get = get.parent
        }
        return res
    }

    private fun fillOrbitTree(
        input: List<Pair<String, String>>,
        orbitTree: OrbitTree
    ) {
        val waitingList = input.toMutableList()
        while (waitingList.size > 0) {
            for (i in 0 until waitingList.size) {
                if (waitingList.size > i) {
                    val it = waitingList[i]
                    if (orbitTree.get(it.first) != null) {
                        orbitTree.addOrbit(it.second, it.first)
                        waitingList.remove(it)
                    }
                }
            }
        }
    }

}

class OrbitTree(rootKey: String) {
    val root: OrbitNode = OrbitNode(rootKey, null)
    var size = 0

    fun addOrbit(key: String, parentKey: String) {
        val parent = get(parentKey)
        val newNode = OrbitNode(key, parent)
        parent?.children?.put(key, newNode)
        size++
    }

    fun get(key: String): OrbitNode? {
        if (key == root.key) {
            return root
        }
        val findChild = root.findChild(key)
        if (findChild != null) {
            return findChild
        }
        return null
    }
}

data class OrbitNode(val key: String, val parent: OrbitNode?) {
    val children: HashMap<String, OrbitNode> = HashMap(1)

    fun findChild(key: String): OrbitNode? {
        if (children.containsKey(key)) {
            return children.get(key)!!
        } else {
            for (i in children.keys) {
                if (children[i]!!.children.isNotEmpty()) {
                    val findChild = children[i]!!.findChild(key)
                    if (findChild != null) {
                        return findChild
                    }
                }
            }
            return null
        }
    }

    fun getDepth(): Int {
        if (parent == null) {
            return 1
        }
        return 1 + parent.getDepth()
    }
}