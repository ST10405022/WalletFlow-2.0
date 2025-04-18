package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Back to Hub screen
        val backBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // TODO: Add category-specific logic later (e.g. fetch title, image, etc.)
    }
}