fun main() {
    val simulation = "a.txt".readInput()

    val incomingStreets: MutableMap<Int, List<Street>> = mutableMapOf()
    val carsWaitingAtSem: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()

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
            var mapForSem: MutableMap<Int, Int>? = null
            if (carsWaitingAtSem.containsKey(street.id)) {
                mapForSem = carsWaitingAtSem[street.id]
            } else {
                mapForSem = mutableMapOf()
            }
            mapForSem.set(time, mapForSem?.getOrDefault(time, 0)?.plus(1))
            carsWaitingAtSem[street.endIntersectionId] = mapForSem
            time += street.duration
        }
    }

    print("$simulation")
}
