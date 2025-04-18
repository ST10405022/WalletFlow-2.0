package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Temporary: Continue without login
        val continueBtn = findViewById<Button>(R.id.continueBtn)
        continueBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish() // Optional: prevents back to login via back button
        }

        // Future: Sign-in button logic can go here
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            // TODO: Add real login logic
        }
    }
}