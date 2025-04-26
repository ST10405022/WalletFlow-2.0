package com.example.prog7313ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateCategoryActivity : AppCompatActivity() {
    private var imageUri: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        // UI references
        val nameInput = findViewById<EditText>(R.id.categoryNameInput)
        val previewLabel = findViewById<TextView>(R.id.categoryPreviewLabel)
        val uploadImageBtn = findViewById<Button>(R.id.uploadImageBtn)
        val previewImage = findViewById<ImageView>(R.id.previewImage)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val backToHubBtn = findViewById<ImageButton>(R.id.backToHubBtn)

        // Back to Main Hub button
        backToHubBtn.setOnClickListener {
            // Go back to HubActivity
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Image selector
        val imageSelector = registerForActivityResult(ActivityResultContracts.GetContent())
        { uri: Uri? ->
            uri?.let {
                imageUri = it.toString()
                previewImage.setImageURI(uri) // Preview the selected image
            }
        }

        // Upload image button
        uploadImageBtn.setOnClickListener {
            imageSelector.launch("image/*")
        }

        nameInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Update the preview label with the current input
                previewLabel.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        // Done button: Save category
        doneButton.setOnClickListener {
            val categoryName = nameInput.text.toString()

            if (categoryName.isNotBlank()) {
                val category = BudgetCategory(name = nameInput.text.toString())

                CoroutineScope(Dispatchers.IO).launch {
                    val budgetCategoryDao = AppDatabase.getInstance(applicationContext).budgetCategoryDao()
                    withContext(Dispatchers.IO){
                        budgetCategoryDao.insertCategory(category)
                    }
                }
                Toast.makeText(this, "Category added", Toast.LENGTH_SHORT).show()
                finish() // Optional: Close this activity after saving
            } else {
                Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
