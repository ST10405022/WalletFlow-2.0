package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prog7313ui.data.AppDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class HubActivity : AppCompatActivity() {
    private lateinit var adapter: CategoryAdapter
    private lateinit var categorySpinner: Spinner // Spinner for selecting a category

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        categorySpinner = findViewById(R.id.categorySpinner)

        // Load categories into the spinner
        loadCategoriesIntoSpinner()
        loadCategoriesIntoRecyclerView()

        // RecyclerView initialization (List of Categories)
        val recyclerView = findViewById<RecyclerView>(R.id.categoryRecyclerView)
        adapter = CategoryAdapter(emptyList()) { category ->
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("Category_ID", category.id)
            intent.putExtra("Category_Name", category.name)
            intent.putExtra("Category_Image", category.imageUri)
            startActivity(intent)
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Add Category Button
        val addCategoryBtn = findViewById<Button>(R.id.addCategoryBtn)
        addCategoryBtn.setOnClickListener {
            val intent = Intent(this, CreateCategoryActivity::class.java)
            startActivity(intent)
        }

        // Add Expense Button
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

        // Bottom Navigation
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_categories -> true // Already here
                R.id.nav_flow -> {
                    Toast.makeText(this, "Flow State tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_achievements -> {
                    Toast.makeText(this, "Achievements tapped", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_expenses -> {
                    val intent = Intent(this, ExpenseListActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }

    private fun loadCategoriesIntoSpinner() {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getInstance(this@HubActivity)
                val categoryDao = db.budgetCategoryDao()

                categoryDao.getAllCategories().collect { categories ->
                    if (categories.isNotEmpty()) {
                        val categoryList = categories.map { "${it.id}. ${it.name}" }

                        val adapter = ArrayAdapter(
                            this@HubActivity,
                            android.R.layout.simple_spinner_item,
                            categoryList
                        )
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        categorySpinner.adapter = adapter
                    } else {
                        Toast.makeText(this@HubActivity, "No categories found", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@HubActivity,
                    "Failed to load categories: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun loadCategoriesIntoRecyclerView() {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getInstance(this@HubActivity)
                val categoryDao = db.budgetCategoryDao()

                categoryDao.getAllCategories().collect { categories ->
                    adapter.updateData(categories)
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@HubActivity,
                    "Failed to load categories: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}