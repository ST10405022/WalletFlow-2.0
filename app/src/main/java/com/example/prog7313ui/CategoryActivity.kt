package com.example.prog7313ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CategoryActivity : AppCompatActivity() {
    private lateinit var categoryTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //Pass category name
        val budgetCategoryName = intent.getStringExtra("Category_Name") ?: "Unnamed Category"
        val titleTextView = findViewById<TextView>(R.id.categoryTitle)
        titleTextView.text = budgetCategoryName

        //Pass category image
        val budgetCategoryImage = intent.getStringExtra("Category_Image")
        val imageCategory = findViewById<ImageView>(R.id.categoryImage)
        budgetCategoryImage?.let {
            val imageUri = Uri.parse(it)
            imageCategory.setImageURI(imageUri)
        }


        // Back to Hub screen
        val backBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        backBtn.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        val categoryName = intent.getStringExtra("Category_Name")

        // TODO: Add category-specific logic later (e.g. fetch title, image, etc.)
    }
}