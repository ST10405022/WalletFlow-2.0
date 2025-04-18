package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddExpenseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // UI elements
        val nameInput = findViewById<EditText>(R.id.inputExpenseName)
        val amountInput = findViewById<EditText>(R.id.inputAmount)
        val dateInput = findViewById<EditText>(R.id.inputDate)
        val descInput = findViewById<EditText>(R.id.inputDescription)
        val categorySpinner = findViewById<Spinner>(R.id.categorySpinner)
        val uploadButton = findViewById<Button>(R.id.uploadReceiptBtn)
        val createExpenseButton = findViewById<Button>(R.id.createExpenseBtn)
        val backButton = findViewById<ImageButton>(R.id.backToHubBtn)

        // Back to HubActivity
        backButton.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Placeholder logic for Create button
        createExpenseButton.setOnClickListener {
            // TODO: Implement save logic
        }

        // Placeholder logic for Upload button
        uploadButton.setOnClickListener {
            // TODO: Open file/image picker
        }
    }
}