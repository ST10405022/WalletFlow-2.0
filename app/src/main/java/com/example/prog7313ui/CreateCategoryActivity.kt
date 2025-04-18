package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CreateCategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        // UI references
        val nameInput = findViewById<EditText>(R.id.categoryNameInput)
        val uploadImageBtn = findViewById<Button>(R.id.uploadImageBtn)
        val previewLabel = findViewById<TextView>(R.id.categoryPreviewLabel)
        val previewImage = findViewById<ImageView>(R.id.previewImage)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val backToHubBtn = findViewById<ImageButton>(R.id.backToHubBtn)

        // Go back to HubActivity
        backToHubBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Placeholder: Done button
        doneButton.setOnClickListener {
            // TODO: Save or return
        }

        // Placeholder: Upload image
        uploadImageBtn.setOnClickListener {
            // TODO: Open image picker
        }
    }
}