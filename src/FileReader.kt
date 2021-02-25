import models.Example
import java.io.File
import java.util.*

fun String.readInput(): Example {
    val file = File(this)
    val scanner = Scanner(file)

    return Example(scanner.nextInt(), "asd")
}