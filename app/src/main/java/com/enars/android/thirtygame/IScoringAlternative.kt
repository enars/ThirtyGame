package com.enars.android.thirtygame

/**
 * Scoring alternatives are the objects visible in the dropdown menu.
 * Depending on what alternative was chosen, the dies are scored differently.
 * All ScoringAlternatives except the one called "Lows" use the initial interface method for
 * calculation.
 */
interface IScoringAlternative {
    val name: String
    val value: Int
    fun calculateScore(dices: Array<Die>): Int {
        val sum = sum(dices)
        when (sum % value ) {
            0 -> return sum
            else -> return -1
        }
    }
    fun sum(dices: Array<Die>): Int {
        return dices.map {d -> d.value}
            .reduce { acc, v -> acc + v} }
}

/**
 * Lows overrides the initial scoring method.
 * Scores every die that has the value 3 or lower by filtering out 4+ and summing the remaining dies
 */
class Lows : IScoringAlternative {
    override val name = "Lows"
    override val value = 3
    override fun calculateScore(dices: Array<Die>): Int {
        val values = dices.map { die -> die.value }
                    .filter { d -> d <= this.value }

        when (values.size) {
            0 -> return -1
            else -> return values.reduce { acc, d -> acc + d }
        }
    }
}

/**
 * The following "Fours" to "Twelves" alternatives use the top level method for scoring.
 * They sum all dies and checks if the <sum modulus(%) scoring value> equals 0, i.e. if it is
 * cleanly divisible by the "value" variable from the scoring alternative.
 */
class Fours : IScoringAlternative {
    override val name = "Fours"
    override val value = 4
}

class Fives : IScoringAlternative {
    override val name = "Fives"
    override val value = 5
}

class Sixes : IScoringAlternative {
    override val name = "Sixes"
    override val value = 6
}

class Sevens : IScoringAlternative {
    override val name = "Sevens"
    override val value = 7
}

class Eights : IScoringAlternative {
    override val name = "Eights"
    override val value = 8
}

class Nines : IScoringAlternative {
    override val name = "Nines"
    override val value = 9
}

class Tens : IScoringAlternative {
    override val name = "Tens"
    override val value = 10
}

class Elevens : IScoringAlternative {
    override val name = "Elevens"
    override val value = 11
}

class Twelves : IScoringAlternative {
    override val name = "Twelves"
    override val value = 12
}