package com.enars.android.thirtygame

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
        return dices.map {d -> d.value}.reduce { acc, v -> acc + v} }
}

/**
 * TODO: Change ScoreCount in each Scoring alternative to its own function
 *       Each Function should take a number of dices depending on user choice
 *       and return a score sum if valid and a -1 if invalid, alerting the player
 *       Lows -> every selected dice is 3 or lower -> return sum
 *       Fours - Twelves -> every selected dice amounts to modulus 0 -> return sum
 *
 */

class Lows : IScoringAlternative {
    override val name = "Lows"
    override val value = 3
    override fun calculateScore(dices: Array<Die>): Int {
        //var dices = dices.filter { d -> d.value <= 3 }

        //if (dices.forEach { d -> return d.value >= 3 }) Check if invalid
        return dices.map { die -> die.value }
                    .filter { d -> d <= 3 }
                    .reduce { acc, d -> acc + d }
    }
}

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