import kotlin.math.max

fun main() {
    val simulation = "a.txt".readInput()

    val incomingStreets: MutableMap<Int, List<Street>> = mutableMapOf()
    val carsWaitingAtSem: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
    val avgCarsAtSem: MutableMap<Int, Float> = mutableMapOf()
    val sumCarsAtSem: MutableMap<Int, Int> = mutableMapOf()
    val maxCarsAtSem: MutableMap<Int, Int> = mutableMapOf()

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

    simulation.streets.forEach { street: Street ->
        val carsAtEachSecond = carsWaitingAtSem[street.id]
        carsAtEachSecond?.values?.forEach { i: Int ->
            sumCarsAtSem[street.id] = sumCarsAtSem.getOrDefault(street.id, 0) + i
            maxCarsAtSem[street.id] = max(i, maxCarsAtSem.getOrDefault(street.id, 0))
        }
        avgCarsAtSem[street.id] = 1f * sumCarsAtSem.getOrDefault(street.id, 0) / simulation.duration
    }

    incomingStreets.keys.forEach { intersectionId: Int ->
        var streetsWithNoCars = 0
        var totalSum = 0
        var maxFromAllStreets = 0
        val streets = incomingStreets[intersectionId]
        streets?.forEach { street: Street ->
            totalSum += sumCarsAtSem.getOrDefault(street.id, 0)
            maxFromAllStreets = max(maxFromAllStreets, maxCarsAtSem.getOrDefault(street.id, 0))
        }
        streets?.forEach { street: Street ->
            if (!sumCarsAtSem.containsKey(street.id)) {
                streetsWithNoCars++
                street.semaphoreDuration = 0
            } else {
                val streetWeight: Float = 1f * sumCarsAtSem[street.id]!! / totalSum
                street.semaphoreDuration = (streetWeight * maxFromAllStreets).toInt()
            }
        }
        println("intersectionId: " + intersectionId)
        println("cate stazi: " + streets?.size?.minus(streetsWithNoCars))
        streets?.forEach { street: Street ->
            if (street.semaphoreDuration != 0) {
                println(street.name + " " + street.semaphoreDuration)
            }
        }

    }

}
