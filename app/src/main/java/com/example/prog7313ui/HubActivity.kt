package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class HubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        // --- Navigation Buttons ---

        // Add Category
        val addCategoryBtn = findViewById<Button>(R.id.addCategoryBtn)
        addCategoryBtn.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

        // Add Expense
        val addExpenseBtn = findViewById<Button>(R.id.addExpenseBtn)
        addExpenseBtn.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            startActivity(intent)
        }

        // Back to Login
        val backToLoginBtn = findViewById<ImageButton>(R.id.backToLoginBtn)
        backToLoginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // --- Category Buttons ---

        val category1Btn = findViewById<Button>(R.id.category1Btn)
        category1Btn.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

        val category2Btn = findViewById<Button?>(R.id.category2Btn)
        category2Btn?.setOnClickListener {
            Toast.makeText(this, "Category 2 clicked", Toast.LENGTH_SHORT).show()
        }

        // --- Bottom Navigation ---
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_categories -> {
                    // Already on this screen
                    true
                }
                R.id.nav_flow -> {
                    // TODO: Navigate to FlowActivity
                    Toast.makeText(this, "Flow State tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_achievements -> {
                    // TODO: Navigate to AchievementsActivity
                    Toast.makeText(this, "Achievements tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_expenses -> {
                    // Navigate to Expense List
                    val intent = Intent(this, ExpenseListActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}