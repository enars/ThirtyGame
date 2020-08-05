@file:Suppress("DEPRECATION")

package com.enars.android.thirtygame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import android.util.Log
import android.view.Gravity

private const val TAG = "MainActivity"
private const val ROUNDS_PER_GAME = 10
private const val THROWS_PER_ROUND = 3

class MainActivity : AppCompatActivity() {

    private lateinit var throwButton: Button
    private lateinit var diceButton0: ImageButton
    private lateinit var diceButton1: ImageButton
    private lateinit var diceButton2: ImageButton
    private lateinit var diceButton3: ImageButton
    private lateinit var diceButton4: ImageButton
    private lateinit var diceButton5: ImageButton

    private lateinit var scoreTextView: TextView
    private lateinit var roundTextView: TextView
    private lateinit var throwsTextView: TextView
    private lateinit var altSpinner: Spinner

    private lateinit var alternatives: ArrayList<String>
    private lateinit var dieButtons: Array<ImageButton>


    private val tgViewModel: ThirtyGameViewModel by lazy {
        ViewModelProviders.of(this).get(ThirtyGameViewModel::class.java)
    }

    /**
     * TODO: SaveInstanceState: Reload all component state and stuff when the app is destroyed
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        throwButton = findViewById(R.id.throw_button)
        diceButton0 = findViewById(R.id.dice_0)
        diceButton1 = findViewById(R.id.dice_1)
        diceButton2 = findViewById(R.id.dice_2)
        diceButton3 = findViewById(R.id.dice_3)
        diceButton4 = findViewById(R.id.dice_4)
        diceButton5 = findViewById(R.id.dice_5)

        scoreTextView = findViewById(R.id.score_text_view)
        roundTextView = findViewById(R.id.round_text_view)
        throwsTextView = findViewById(R.id.throws_text_view)
        altSpinner = findViewById(R.id.scoring_alt_spinner)

        alternatives = ArrayList<String>()
        updateSpinner()

        dieButtons =
            arrayOf(diceButton0, diceButton1, diceButton2, diceButton3, diceButton4, diceButton5)

        diceButton0.setOnClickListener {
            tgViewModel.toggleSaveDie(0)
            updateDiceImages()
        }
        diceButton1.setOnClickListener {
            tgViewModel.toggleSaveDie(1)
            updateDiceImages()
        }
        diceButton2.setOnClickListener {
            tgViewModel.toggleSaveDie(2)
            updateDiceImages()
        }
        diceButton3.setOnClickListener {
            tgViewModel.toggleSaveDie(3)
            updateDiceImages()
        }
        diceButton4.setOnClickListener {
            tgViewModel.toggleSaveDie(4)
            updateDiceImages()
        }
        diceButton5.setOnClickListener {
            tgViewModel.toggleSaveDie(5)
            updateDiceImages()
        }

        // Determines the flow of the game
        throwButton.setOnClickListener {
            when { tgViewModel.throws == THROWS_PER_ROUND -> scoreRound()
                else -> nextThrow()
            }
        }

        //START GAME
        //Do not throw dice on every onCreate, only when the game begins
        if (tgViewModel.rounds.size == 0 && tgViewModel.throws == 0)
            nextThrow()
        else {
            updateDiceImages()
            updateScreenTexts()
        }
    }

    /**
     * Throw the dice and update the UI accordingly
     */
    private fun nextThrow() {
        tgViewModel.throws++
        tgViewModel.throwDies()

        updateDiceImages()
        updateScreenTexts()
    }

    /**
     * Check if game is over, otherwise check scoring and start next round
     */
    private fun scoreRound() {
        // Check game over
        if ((tgViewModel.rounds.size + 1) == ROUNDS_PER_GAME)
            scoreGame()
        else {
            val selectedSA = altSpinner.selectedItem.toString()
            val scoringCheck = tgViewModel.checkValidScore(selectedSA)
            when (scoringCheck) {
                "empty" -> createToast(R.string.empty_selection_toast)
                "invalid" -> createToast(R.string.invalid_toast)
                "valid" ->  {
                    //Start Next Round
                    tgViewModel.addRound(selectedSA)
                    tgViewModel.throws = 0
                    tgViewModel.unToggleAllDies()
                    updateSpinner()
                    nextThrow()
                }
            }
        }
    }

    /**
     * Send user to score activity to display the final score
     */
    private fun scoreGame() {
        val finalScore = tgViewModel.score
        val intent: Intent = FinalScoreActivity.newIntent(this@MainActivity, finalScore)
        startActivity(intent)
    }

    /**
     * Get dies from models, map dies with respective onscreen imageButton, call set images
     */
    private fun updateDiceImages() {
        dieButtons.zip(tgViewModel.dies)
            .toMap()
            .forEach { (image, die) -> setDieImage(image, die) }
    }

    /**
     * Determines what image is to be used for each die
     */
    private fun setDieImage(diceButton: ImageButton, die: Die) {
        val color = when {
            die.isSaved -> "grey"
            else -> "white"
        }
        val imageUri = color + die.value
        val imageResId = this.resources.getIdentifier(imageUri, "drawable", this.packageName)
        diceButton.setImageResource(imageResId)
    }

    /**
     * Update buttons and textviews text
     */
    private fun updateScreenTexts() {
        scoreTextView.setText("" + tgViewModel.score)
        throwsTextView.setText("" + tgViewModel.throws)
        roundTextView.setText("" + (tgViewModel.rounds.size + 1))
        when {
            tgViewModel.throws == THROWS_PER_ROUND -> throwButton.setText(R.string.score_button)
            else -> throwButton.setText(R.string.throw_button)
        }
    }

    /**
     * Get updated spinner list, attach it to the spinner
     */
    private fun updateSpinner() {
        alternatives = createSpinnerList()
        val adp =  ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, alternatives)
        altSpinner.adapter = adp
    }

    /**
     * Get an updated scoring alternatives list for the spinner
     */
    private fun createSpinnerList(): ArrayList<String> {
        val namesList = tgViewModel.getAvailableAlternatives()
        val namesArrayList = namesList.toCollection(ArrayList())

        return namesArrayList
    }

    private fun createToast(toastMessage: Int) {
        val toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0,0)
        toast.show()
    }
}