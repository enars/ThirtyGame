package com.enars.android.thirtygame

interface IScoringAlternative {
    val name: String
    fun calculateScore(dices: Array<Die>): Int
}

class Lows : IScoringAlternative {
    override val name = "Lows"
    override fun calculateScore(dices: Array<Die>): Int {
        var dices = dices.filter { d -> d.value <= 3 }
        val score: Int = dices.count()
        return score
    }
}

class Fours : IScoringAlternative {
    override val name = "Fours"
    override fun calculateScore(dices: Array<Die>): Int {
        val sum = dices.map { d -> d.value }.sum()
        val score = sum / 4
        return score
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