package com.example.prog7313ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prog7313ui.R.id.categoryTitle
import com.example.prog7313ui.data.AppDatabase
import kotlinx.coroutines.launch
import androidx.core.net.toUri

class CategoryActivity : AppCompatActivity() {
    private lateinit var adapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //UI references
        val titleTextView = findViewById<TextView>(categoryTitle)
        val imageCategory = findViewById<ImageView>(R.id.categoryImage)
        val txtMinLimit = findViewById<TextView>(R.id.inputMinValue)
        val txtMaxLimit = findViewById<TextView>(R.id.inputMaxValue)

        //Pass category name, id, and image
        val budgetCategoryId = intent.getIntExtra("CATEGORY_ID" , -1)
        val budgetCategoryName = intent.getStringExtra("CATEGORY_NAME")
        val budgetCategoryImage = intent.getStringExtra("CATEGORY_IMAGE")
        val budgetCategoryMinLimit = intent.getDoubleExtra("Category_MINLIMIT", 0.0)
        val budgetCategoryMaxLimit = intent.getDoubleExtra("Category_MAXLIMIT", 0.0)

        titleTextView.text = budgetCategoryName     // Set category title

        budgetCategoryImage?.let {
            val imageUri = it.toUri()
            imageCategory.setImageURI(imageUri)     // Set category image
        }

        txtMinLimit.text = budgetCategoryMinLimit.toString()    // Set category min limit
        txtMaxLimit.text = budgetCategoryMaxLimit.toString()    // Set category max limit


        // Back to Hub screen
        val backBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnOk = findViewById<Button>(R.id.updateButton)
        btnOk.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // TODO: Add category-specific logic later (e.g. fetch title, image, etc.)

        // Expenses recyclerView initialization
        adapter = ExpenseAdapter(ExpenseListActivity())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewExpenses)    // (Android, 2025)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        //Load expenses
        loadCategoryDetails(budgetCategoryId, txtMinLimit, txtMaxLimit)
    }

    private fun loadCategoryDetails(
        categoryId: Int,
        minLimit: TextView,
        maxLimit: TextView
    ) {
        lifecycleScope.launch {
            try {
                val db = AppDatabase.getInstance(this@CategoryActivity)
                val expenseDao = db.expenseDao()

                val category = db.budgetCategoryDao().getCategoryId(categoryId)
                minLimit.text = category?.minLimit.toString()
                maxLimit.text = category?.maxLimit.toString()

                expenseDao.getAllExpenses().collect { expenses ->
                    adapter.submitList(expenses)
                }

                db.expenseDao().getExpensesByCategory(categoryId).collect{
                        expenses -> adapter.submitList(expenses)
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

/*  Reference List
*       Android. 2025. Create dynamic lists with RecyclerView:   views:   Android developers,
*           Android Developers. [Online].
*           Available at: https://developer.android.com/develop/ui/views/layout/recyclerview
*           (Accessed: 01 May 2025).
* */