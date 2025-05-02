package com.example.historycards

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class QuizActivity : AppCompatActivity() {
    // Parallel arrays for questions and answers
    private val questions = arrayOf(
        "Nelson Mandela was the president in 1994.",
        "World War II ended in 1945.",
        "The Berlin Wall fell in 1989.",
        "The French Revolution occurred in the 18th century.",
        "The United States declared independence in 1776."
    )

    private val answers = booleanArrayOf(true, true, true, true, true)

    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        // Initialize UI components
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        val trueButton = findViewById<Button>(R.id.trueButton)
        val falseButton = findViewById<Button>(R.id.falseButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        // Display the first question
        showQuestion(questionTextView)

        // True button click handler
        trueButton.setOnClickListener {
            checkAnswer(true, questionTextView)
        }

        // False button click handler
        falseButton.setOnClickListener {
            checkAnswer(false, questionTextView)
        }

        // Next button click handler
        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                showQuestion(questionTextView)
                // Reset feedback for new question
                questionTextView.setBackgroundResource(android.R.color.transparent)
            } else {
                // All questions answered, show results
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("SCORE", score)
                intent.putExtra("TOTAL_QUESTIONS", questions.size)
                intent.putExtra("QUESTIONS", questions)
                intent.putExtra("ANSWERS", answers)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion(textView: TextView) {
        textView.text = questions[currentQuestionIndex]
    }

    private fun checkAnswer(userAnswer: Boolean, textView: TextView) {
        val correctAnswer = answers[currentQuestionIndex]

        if (userAnswer == correctAnswer) {
            score++
            textView.setBackgroundResource(R.color.correct_answer)
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            textView.setBackgroundResource(R.color.incorrect_answer)
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
        }
    }
}