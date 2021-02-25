fun main() {
    val simulation = "a.txt".readInput()

    val incomingStreets: MutableMap<Int, List<Street>> = mutableMapOf()
//    val carsWaitingAtInters: Muta

    (0 until simulation.intersectionsNumber).forEach { intersectionId ->
        val streets = simulation.streets.filter { street ->
            street.endIntersectionId == intersectionId
        }

        incomingStreets[intersectionId] = streets
    }

    incomingStreets.keys.forEach { intersectionId ->
        println("$intersectionId : ${incomingStreets[intersectionId]}")
    }

    simulation.cars.forEach { car: Car ->
        var time = 0
        car.streetsNames.forEach { s: String ->
            val street: Street = simulation.streets[simulation.streetNameToId[s]!!]

        }
    }

    print("$simulation")
}
