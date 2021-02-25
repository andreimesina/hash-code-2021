
fun main() {
    val simulation = "a.txt".readInput()

    var incomingStreetsForEachIntersection: MutableMap<Int, List<Int>> = mutableMapOf()
    simulation.streets.forEach { street: Street ->
        if (incomingStreetsForEachIntersection.containsKey(street.endIntersectionId)) {
            val l: List<Int>? = incomingStreetsForEachIntersection[street.endIntersectionId]
//            l.ad
        }
    }

    print("$simulation")
}

fun