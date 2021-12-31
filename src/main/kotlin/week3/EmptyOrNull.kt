package week3

fun main(args: Array<String>) {
    val s1: String? = null
    val s2: String? = ""
    s1.isEmptyOrNull() //eq true
    s2.isEmptyOrNull() //eq true

    val s3 = "   "
    s3.isEmptyOrNull() //eq false
}

fun String?.isEmptyOrNull() : Boolean {
    if (this.isNullOrEmpty()) return true
    return false
}