package week3

fun main(args: Array<String>) {
    val s = "any string"
    println(s as? Int)    // null
    println(s as Int?)    // exception
}