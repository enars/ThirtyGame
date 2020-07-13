package com.enars.android.thirtygame

import androidx.lifecycle.ViewModel

class ThirtyGameViewModel : ViewModel() {
    var score = 0
    var throws = 0

    val dies = arrayOf(
        Die(),
        Die(),
        Die(),
        Die(),
        Die(),
        Die()
    )

    val scoringAlternatives = listOf<IScoringAlternative>(
        Lows(),
        Fours(),
        Fives(),
        Sixes(),
        Sevens(),
        Eights(),
        Nines(),
        Tens(),
        Elevens(),
        Twelves()
    )

    var rounds = listOf<Round>()

    /**
     * For each die; if die is saved -> do nothing, otherwise -> throw
     */
    fun throwDies() {
        dies.forEach { die -> if (die.isSaved) null else die.throwDie() }
    }

    /**
     * Toggle dice background: If die is unsaved -> save, if die is saved -> unsave
     */
    fun toggleSaveDie(index: Int) {
        dies[index].isSaved = !dies[index].isSaved
    }

    fun unToggleAllDies() {
        dies.forEach { die -> die.isSaved = false }
    }

    fun getScoringAlternativeByName(name: String): IScoringAlternative {
       return scoringAlternatives.filter { sa -> sa.name == name }.first()
    }

    /**
     * Filter out all already chosen alternatives and return "available" scoring options by:
     *  - Group all common items between used alternatives and all alternatives lists
     *  - Save all single options (1 occurence)
     *  - Flatten remaining scoring alternatives into a single list
     *  - Return
     */
    fun getAvailableAlternatives(): List<String> {
        val allAlts = (scoringAlternatives.map { a -> a.name })
        val chosenAlts = (rounds.map { r -> r.scoringAlternative.name })
        val sum = allAlts + chosenAlts
        val availableAlternatives = sum.groupBy { it }
            .filter { it.value.size == 1 }
            .flatMap { it.value }

        return availableAlternatives
    }

    /**
     * Create and add the new round to the rounds array.
     * Add chosen scoring alternative to used alternatives list
     * calculate round score
     */
    fun addRound(selectedAlternative: String) {
        val scoreAlternative = getScoringAlternativeByName(selectedAlternative)
        val saveRound = Round(dies, scoreAlternative)
        val newRounds = rounds + saveRound
        rounds = newRounds

        //usedAlternatives.add(scoreAlternative)
        score += scoreAlternative.calculateScore(saveRound.dices)
    }


}