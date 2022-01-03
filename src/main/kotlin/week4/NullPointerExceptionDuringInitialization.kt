package week4

open class NullPointerExceptionDuringInitialization(open val value: String) {
    init {
        value.length
    }
}

class B(override val value: String) : NullPointerExceptionDuringInitialization(value)

fun main(args: Array<String>) {
    B("a")
}