package com.enars.android.thirtygame

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_FINAL_SCORE = "com.enars.android.thirtygame.final_score"

class FinalScoreActivity : AppCompatActivity() {

    private lateinit var scoreTextView: TextView

    private var finalScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_score)

        finalScore = intent.getIntExtra(EXTRA_FINAL_SCORE, 0)
        scoreTextView = findViewById(R.id.score_text_view)
        scoreTextView.setText("" + finalScore)
    }

    companion object {
        fun newIntent(packageContext: Context, finalScore: Int): Intent {
            return Intent(packageContext, FinalScoreActivity::class.java).apply {
                putExtra(EXTRA_FINAL_SCORE, finalScore)
            }
        }
    }
}