package nicestring

fun String.isNice(): Boolean {
    val conditions = listOf(
        ::doesNotContainSubstrings,
        ::containsAtLeastThreeVowels,
        ::containsDoubleLetter
    )
    return conditions.count { run(it) } >= 2
}

fun String.doesNotContainSubstrings(): Boolean {
    val containsStrings = this.contains("bu", true)
            || this.contains("ba", true)
            || this.contains("be", true)
    return !containsStrings
}

fun String.containsAtLeastThreeVowels(): Boolean {
    return this.count { it in "aeiou" } >= 3
}

fun String.containsDoubleLetter(): Boolean {
    for (i in 0 until this.length - 1) {
        if (this[i] == this[i + 1]) return true
    }
    return false
}