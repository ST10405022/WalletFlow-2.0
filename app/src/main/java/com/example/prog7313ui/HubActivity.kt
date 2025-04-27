package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class HubActivity : AppCompatActivity() {
    private lateinit var adapter: CategoryAdapter
    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        // RecyclerView initialization
        val recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        adapter = CategoryAdapter(emptyList()){
            budgetCategory -> val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("Category_ID", budgetCategory.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // RoomDB data
        categoryViewModel.allCategories.observe(this){
            categories -> adapter.updateData(categories)
        }

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