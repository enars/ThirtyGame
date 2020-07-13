package com.enars.android.thirtygame

/**
 * Stores data for a single round
 */
data class Round(val dices: Array<Die>, val scoringAlternative: IScoringAlternative)