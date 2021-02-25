fun main() {
    val simulation = "a.txt".readInput()

    val incomingStreets: MutableMap<Int, List<Street>> = mutableMapOf()

    (0 until simulation.intersectionsNumber).forEach { intersectionId ->
        val streets = simulation.streets.filter { street ->
            street.endIntersectionId == intersectionId
        }

        incomingStreets[intersectionId] = streets
    }

    incomingStreets.keys.forEach { intersectionId ->
        println("$intersectionId : ${incomingStreets[intersectionId]}")
    }

    print("$simulation")
}
