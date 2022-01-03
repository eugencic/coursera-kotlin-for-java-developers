package week4

val foo: Int
    get() = (0..10).random()

fun main(args: Array<String>) {
    // The values should be different:
    println(foo)
    println(foo)
    println(foo)
}