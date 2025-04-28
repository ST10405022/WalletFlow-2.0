package com.example.prog7313ui

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ExpenseListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        // Return to Hub
        val backBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        val listContainer = findViewById<LinearLayout>(R.id.expenseListContainer)

        // Sample static data
        val items = listOf(
            "List item", "List item", "List item", "List item",
            "List item", "List item", "List item", "List item", "List item", "List item"
        )
        val highlightedIndexes = listOf(0, 4, 7)

        items.forEachIndexed { index, item ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                setPadding(16, 16, 16, 16)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 12)
                }
                setBackgroundColor(
                    if (index in highlightedIndexes) Color.parseColor("#F44336")
                    else Color.parseColor("#F5EFFF")
                )
            }

            val icon = TextView(this).apply {
                text = "A"
                gravity = Gravity.CENTER
                setTextColor(Color.WHITE)
                textSize = 16f
                setPadding(16, 16, 16, 16)
                background = resources.getDrawable(R.drawable.circle_background, null)
            }

            val label = TextView(this).apply {
                text = item
                textSize = 16f
                setTextColor(Color.BLACK)
                setPadding(24, 0, 0, 0)
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            }

            val checkbox = CheckBox(this)

            row.addView(icon)
            row.addView(label)
            row.addView(checkbox)

            listContainer.addView(row)
        }
    }
}