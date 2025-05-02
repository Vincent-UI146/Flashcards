package com.example.historycards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Welcome message and app description
        val welcomeMessage = findViewById<TextView>(R.id.welcomeMessage)
        welcomeMessage.text = getString(R.string.welcome_message)

        // Start button to begin the quiz
        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            // Start the QuizActivity when button is clicked
            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}