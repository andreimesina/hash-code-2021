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

//    incomingStreets.keys.forEach { intersectionId ->
//        println("$intersectionId : ${incomingStreets[intersectionId]}")
//    }

    simulation.cars.forEach { car: Car ->
        var time = 0

        car.streetsNames.forEachIndexed { index, s: String ->
            val street: Street = simulation.streets.first { it.name == s }

            val mapForSem: MutableMap<Int, Int>? = if (carsWaitingAtSem.containsKey(street.id)) {
                carsWaitingAtSem[street.id]
            } else {
                mutableMapOf()
            }

            if (index > 0)
                time += street.duration

            mapForSem?.set(time, mapForSem.getOrDefault(time, 0).plus(1))
            carsWaitingAtSem[street.id] = mapForSem ?: mutableMapOf()

            println("endInterId: ${street.endIntersectionId} - car: ${car.id} - second: $time")
        }
    }

}
