package com.example.les

import android.os.Bundle
import android.content.Intent

import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AnswerActivity : AppCompatActivity() {
    private lateinit var correctAnswer: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        correctAnswer = intent.getStringExtra("answer") ?: ""

        val answerEditText = findViewById<EditText>(R.id.answerEditText)
        val checkButton = findViewById<Button>(R.id.checkButton)

        checkButton.setOnClickListener {
            val userAnswer = answerEditText.text.toString()
            checkAnswer(userAnswer)
        }
    }

    private fun checkAnswer(userAnswer: String) {
        val isCorrect = userAnswer.trim().equals(correctAnswer, ignoreCase = true)
        val resultIntent = Intent().apply {
            putExtra("isCorrect", isCorrect)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}
