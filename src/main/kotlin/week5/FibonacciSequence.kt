package week5

fun fibonacci(): Sequence<Int> = sequence {
    var v1 = 0
    var v2 = 1

    while (true) {
        yield(v1)
        val nextVal = v1 + v2
        v1 = v2
        v2 = nextVal
    }
}

fun main(args: Array<String>) {
    fibonacci().take(4).toList().toString() //eq
            "[0, 1, 1, 2]"

    fibonacci().take(10).toList().toString() //eq
            "[0, 1, 1, 2, 3, 5, 8, 13, 21, 34]"
}