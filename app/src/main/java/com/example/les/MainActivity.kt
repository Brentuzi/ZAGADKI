package com.example.les

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.content.Intent

import android.widget.Button
import android.widget.TextView

import kotlin.random.Random
data class Riddle(val question: String, val answer: String)
class MainActivity : AppCompatActivity() {
    private lateinit var riddles: MutableList<Riddle>
    private lateinit var currentRiddle: Riddle
    private var correctAnswers = 0
    private var incorrectAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeRiddles()

        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val answerButton = findViewById<Button>(R.id.answerButton)
        val nextButton = findViewById<Button>(R.id.nextButton)
        val statisticsButton = findViewById<Button>(R.id.statisticsButton)

        loadRandomRiddle(questionTextView)

        answerButton.setOnClickListener {
            val intent = Intent(this, AnswerActivity::class.java)
            intent.putExtra("answer", currentRiddle.answer)
            startActivityForResult(intent, 1)
        }

        nextButton.setOnClickListener {
            loadRandomRiddle(questionTextView)
        }

        statisticsButton.setOnClickListener {
            val intent = Intent(this, StatisticsActivity::class.java)
            startActivity(intent)
        }

        loadStats()
    }

    override fun onPause() {
        super.onPause()
        saveStats()
    }

    private fun initializeRiddles() {
        riddles = mutableListOf(
            Riddle("Загадка 1", "Ответ 1"),
            Riddle("Загадка 2", "Ответ 2"),
            Riddle("Загадка 2", "Ответ 2"),
            Riddle("Загадка 2", "Ответ 2"),
            Riddle("Загадка 2", "Ответ 2"),
            Riddle("Загадка 2", "Ответ 2"),
            Riddle("Загадка 2", "Ответ 2")

        )
    }

    private fun loadRandomRiddle(textView: TextView) {
        if (riddles.isEmpty()) {
            textView.text = "Все загадки закончились!"
            return
        }
        val randomIndex = Random.nextInt(riddles.size)
        currentRiddle = riddles[randomIndex]
        textView.text = currentRiddle.question
        riddles.removeAt(randomIndex)
    }

    private fun loadStats() {
        val sharedPreferences = getSharedPreferences("stats", Context.MODE_PRIVATE)
        correctAnswers = sharedPreferences.getInt("correctAnswers", 0)
        incorrectAnswers = sharedPreferences.getInt("incorrectAnswers", 0)
    }

    private fun saveStats() {
        val sharedPreferences = getSharedPreferences("stats", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .putInt("correctAnswers", correctAnswers)
            .putInt("incorrectAnswers", incorrectAnswers)
            .apply()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                val isCorrect = data?.getBooleanExtra("isCorrect", false) ?: false
                if (isCorrect) {
                    correctAnswers++
                } else {
                    incorrectAnswers++
                }
            }
        }
    }
}
