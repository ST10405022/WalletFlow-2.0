package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prog7313ui.R.id.categoryTitle
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.ExpenseAdapter
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class CategoryActivity : AppCompatActivity() {
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //UI references
        val categoryName = intent.getStringExtra("Category_Name")
        val categoryTitle = findViewById<TextView>(R.id.categoryTitle)

        categoryTitle.text = categoryName

        // Back to Hub screen
        val backBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // TODO: Add category-specific logic later (e.g. fetch title, image, etc.)

        //Load expenses
        loadExpensesIntoRecyclerView()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewExpenses)
        adapter = ExpenseAdapter(emptyList()) { expense ->
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("Expense_ID", expense.id)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun loadExpensesIntoRecyclerView() {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getInstance(this@CategoryActivity)
                val expenseDao = db.expenseDao()

                expenseDao.getAllExpenses().collect { expenses ->
                    adapter.updateData(expenses)
                }

            } catch (e: Exception) {
                Toast.makeText(
                    this@CategoryActivity,
                    "Failed to load categories: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}