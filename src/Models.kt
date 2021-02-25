data class Simulation(
    val duration: Int,
    val intersectionsNumber: Int,
    val streetsNumber: Int,
    val carsNumber: Int,
    val bonusPoints: Int,
    val streets: List<Street>,
    val streetNameToId: MutableMap<String, Int>,
    val cars: List<Car>
)

data class Street(
    val id: Int,
    val startIntersectionId: Int,
    val endIntersectionId: Int,
    val name: String,
    val duration: Int
) {
    var semaphoreDuration: Int = 1
}

data class Car(
    val id: Int,
    val streetsNumber: Int,
    val streetsNames: List<String>
)