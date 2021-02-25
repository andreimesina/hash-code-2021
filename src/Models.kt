data class Simulation(
    val duration: Int,
    val intersectionsNumber: Int,
    val streetsNumber: Int,
    val carsNumber: Int,
    val bonusPoints: Int,
    val streets: List<Street>,
    val cars: List<Car>
)

data class Street(
    val startIntersectionId: Int,
    val endIntersectionId: Int,
    val name: String,
    val duration: Int
)

data class Car(
    val streetsNumber: Int,
    val streetsNames: List<String>
)