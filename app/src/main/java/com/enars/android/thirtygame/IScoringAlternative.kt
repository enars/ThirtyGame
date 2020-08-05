package com.enars.android.thirtygame

interface IScoringAlternative {
    val name: String
    fun calculateScore(dices: Array<Die>): Int
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
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map {d -> d.value}
                            .reduce { acc, v -> acc + v}
        when (sum % 4 ) {
            0 -> return sum
            else -> return -1
        }
    }
}

class Fives : IScoringAlternative {
    override val name = "Fives"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 5
        return score
    }
}

class Sixes : IScoringAlternative {
    override val name = "Sixes"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 6
        return score
    }
}

class Sevens : IScoringAlternative {
    override val name = "Sevens"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 7
        return score
    }
}

class Eights : IScoringAlternative {
    override val name = "Eights"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 8
        return score
    }
}

class Nines : IScoringAlternative {
    override val name = "Nines"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 9
        return score
    }
}

class Tens : IScoringAlternative {
    override val name = "Tens"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 10
        return score
    }
}

class Elevens : IScoringAlternative {
    override val name = "Elevens"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 11
        return score
    }
}

class Twelves : IScoringAlternative {
    override val name = "Twelves"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 12
        return score
    }
}