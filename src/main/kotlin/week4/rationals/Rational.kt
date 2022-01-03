package rationals

import java.math.BigInteger
import java.math.BigInteger.ZERO
import java.math.BigInteger.ONE
import java.lang.IllegalArgumentException

class Rational(private val numerator: BigInteger, private val denominator: BigInteger) : Comparable<Rational> {
    init {
        if (denominator == ZERO) {
            throw IllegalArgumentException()
        }
    }

    private fun normalize(): Rational {
        val gcd = numerator.gcd(denominator)
        var n = numerator / gcd
        var d = denominator / gcd

        if (d < ZERO) {
            n = -n
            d = -d
        }

        return Rational(n, d)
    }

    operator fun unaryMinus(): Rational = Rational(-numerator, denominator)

    operator fun plus(another: Rational): Rational {
        val n = numerator * another.denominator + another.numerator * denominator
        val d = denominator * another.denominator

        return n divBy d
    }

    operator fun minus(another: Rational): Rational {
        val n = numerator * another.denominator - another.numerator * denominator
        val d = denominator * another.denominator

        return n divBy d
    }

    operator fun times(another: Rational): Rational {
        val n = numerator * another.numerator
        val d = denominator * another.denominator

        return n divBy d
    }

    operator fun div(another: Rational): Rational {
        val n = numerator * another.denominator
        val d = denominator * another.numerator

        return n divBy d
    }

    override fun toString(): String {
        return if (denominator == ONE || numerator % denominator == ZERO) {
            (numerator / denominator).toString()
        } else {
            val r = normalize()

            return "${r.numerator}/${r.denominator}"
        }
    }

    override fun hashCode(): Int {
        var r = numerator.hashCode()

        r = 31 * r + denominator.hashCode()

        return r
    }

    override fun equals(another: Any?): Boolean {
        if (this === another) return true
        if (another !is Rational) return false

        val a = this.normalize()
        val b = another.normalize()

        return a.numerator == b.numerator && a.denominator == b.denominator
    }

    override fun compareTo(another: Rational): Int {
        val a = this.numerator * another.denominator divBy this.denominator * another.denominator
        val b = another.numerator * this.denominator divBy another.denominator * this.denominator

        return (a.numerator).compareTo(b.numerator)
    }
}

fun String.toRational(): Rational {
    val args = split("/")

    return when (args.size) {
        1 -> Rational(args[0].toBigInteger(), 1.toBigInteger())
        2 -> Rational(args[0].toBigInteger(), args[1].toBigInteger())
        else -> throw IllegalArgumentException()
    }
}

infix fun Int.divBy(another: Int): Rational = Rational(this.toBigInteger(), another.toBigInteger())
infix fun Long.divBy(another: Long): Rational = Rational(this.toBigInteger(), another.toBigInteger())
infix fun BigInteger.divBy(another: BigInteger): Rational = Rational(this, another)

fun main() {
    val half = 1 divBy 2
    val third = 1 divBy 3

    val sum: Rational = half + third
    println(5 divBy 6 == sum)

    val difference: Rational = half - third
    println(1 divBy 6 == difference)

    val product: Rational = half * third
    println(1 divBy 6 == product)

    val quotient: Rational = half / third
    println(3 divBy 2 == quotient)

    val negation: Rational = -half
    println(-1 divBy 2 == negation)

    println((2 divBy 1).toString() == "2")
    println((-2 divBy 4).toString() == "-1/2")
    println("117/1098".toRational().toString() == "13/122")

    val twoThirds = 2 divBy 3
    println(half < twoThirds)

    println(half in third..twoThirds)

    println(2000000000L divBy 4000000000L == 1 divBy 2)

    println("912016490186296920119201192141970416029".toBigInteger() divBy
            "1824032980372593840238402384283940832058".toBigInteger() == 1 divBy 2)
}