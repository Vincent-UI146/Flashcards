package com.example.historycards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Get data from intent
        val score = intent.getIntExtra("SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)
        val questions = intent.getStringArrayExtra("QUESTIONS") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("ANSWERS") ?: booleanArrayOf()

        // Initialize UI components
        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val feedbackTextView = findViewById<TextView>(R.id.feedbackTextView)
        val reviewButton = findViewById<Button>(R.id.reviewButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        // Display score
        scoreTextView.text = "Your score: $score out of $totalQuestions"

        // Provide feedback based on score
        val feedback = if (score >= 3) {
            "Great job! You know your history well!"
        } else {
            "Keep practicing! History is important to learn."
        }
        feedbackTextView.text = feedback

        // Review button shows all questions and answers
        reviewButton.setOnClickListener {
            val reviewMessage = buildReviewMessage(questions, answers)
            feedbackTextView.text = reviewMessage
        }

        // Exit button closes the app
        exitButton.setOnClickListener {
            finishAffinity() // Close all activities and exit app
        }
    }

    private fun buildReviewMessage(questions: Array<String>, answers: BooleanArray): String {
        val builder = StringBuilder("Review:\n\n")
        for (i in questions.indices) {
            builder.append("Q: ${questions[i]}\n")
            builder.append("A: ${if (answers[i]) "True" else "False"}\n\n")
        }
        return builder.toString()
    }
}