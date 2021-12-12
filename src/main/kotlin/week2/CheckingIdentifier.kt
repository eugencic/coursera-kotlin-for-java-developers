package week2

fun isValidIdentifier(s: String): Boolean {
    fun isValid(c: Char) = c == '_' || c.isLetter() || c.isDigit()

    if (s.isEmpty()) return false
    if (s.first().isDigit()) return false
    for (c in s) {
        if(!isValid(c)) return false
    }

    return true
}

fun main(args: Array<String>) {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}