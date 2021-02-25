import java.io.File
import java.util.*

fun String.readInput(): Simulation {
    val file = File(this)
    val scanner = Scanner(file)

    val duration = scanner.nextInt()
    val intersectionsNumber = scanner.nextInt()
    val streetsNumber = scanner.nextInt()
    val carsNumber = scanner.nextInt()
    val bonusPoints = scanner.nextInt()

    val streets = (0 until streetsNumber).map {
        val startId = scanner.nextInt()
        val endId = scanner.nextInt()
        val name = scanner.next()
        val streetDuration = scanner.nextInt()


        Street(it, startId, endId, name, streetDuration)
    }

    val cars = (0 until carsNumber).map {
        val carStreetsNumber = scanner.nextInt()

        val streetsNames = (0 until carStreetsNumber).map {
            scanner.next()
        }

        Car(it, carStreetsNumber, streetsNames)
    }

    val streetNameToId = mutableMapOf<String, Int>()
    streets.forEach { street -> streetNameToId[street.name] = street.id }

    return Simulation(
        duration,
        intersectionsNumber,
        streetsNumber,
        carsNumber,
        bonusPoints,
        streets,
        streetNameToId,
        cars
    )
}