package com.enars.android.thirtygame

/**
 * A Die implementation that can saved (i. e. not thrown)
 */
class Die {

    var value: Int = 1
    var isSaved: Boolean = false

    /**
     * Throw die and set the die value to a number between 1 - 6
     */
    fun throwDie() {
        value = (Math.random() * (6 - 1 + 1)).toInt() + 1
    }
}