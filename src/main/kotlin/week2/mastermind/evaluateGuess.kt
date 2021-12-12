package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = 'R'
    val wrongPosition = 'W'
    val secretArr: CharArray = secret.toCharArray()
    val guessArr: CharArray = guess.toCharArray()

    secretArr.forEachIndexed { i, letter ->
        if (guessArr[i] == letter) {
            secretArr[i] = rightPosition
            guessArr[i] = rightPosition
        }
    }

    secretArr.forEachIndexed { i, letter ->
        if (letter != 'R' && guessArr.contains(letter)) {
            secretArr[i] = wrongPosition
            guessArr[guessArr.indexOf(letter)] = wrongPosition
        }
    }

    return Evaluation(secretArr.count { it == rightPosition }, secretArr.count { it == wrongPosition })
}