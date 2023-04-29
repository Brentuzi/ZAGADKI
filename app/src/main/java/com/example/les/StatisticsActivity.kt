package com.example.les

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StatisticsActivity : AppCompatActivity() {
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        loadStats()

        val correctAnswersTextView = findViewById<TextView>(R.id.correctAnswersTextView)
        val incorrectAnswersTextView = findViewById<TextView>(R.id.incorrectAnswersTextView)

        correctAnswersTextView.text = "Правильных ответов: $correctAnswers"
        incorrectAnswersTextView.text = "Неправильных ответов: $incorrectAnswers"

        val homeButton = findViewById<Button>(R.id.homeButton)
        homeButton.setOnClickListener {
            finish()
        }
    }

    private fun loadStats() {
        val sharedPreferences = getSharedPreferences("stats", MODE_PRIVATE)
        correctAnswers = sharedPreferences.getInt("correctAnswers", 0)
        incorrectAnswers = sharedPreferences.getInt("incorrectAnswers", 0)
    }
}