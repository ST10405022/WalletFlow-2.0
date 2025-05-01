package com.example.prog7313ui

import android.content.Intent
import android.net.Uri
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.BudgetCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateCategoryActivity : AppCompatActivity() {
    private var imageUri: Uri? = null    // selected Image Uri

    // Explicit launcher for picking an image using the MediaStore (via SAF)
    @RequiresApi(Build.VERSION_CODES.P)
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            imageUri = it

            // Allow long-term access
            contentResolver.takePersistableUriPermission(
                it,
                Intent.FLAG_GRANT_READ_URI_PERMISSION
            )

            Toast.makeText(
                this, "Photo selected!",
                Toast.LENGTH_SHORT
            ).show() // Inform user of selection
        } ?: run {
            Toast.makeText(
                this, "No photo selected",
                Toast.LENGTH_SHORT
            ).show() // Inform user of no selection
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        // UI references
        val nameInput = findViewById<EditText>(R.id.categoryNameInput)
        val previewLabel = findViewById<TextView>(R.id.categoryPreviewLabel)
        val uploadImageBtn = findViewById<Button>(R.id.uploadImageBtn)
        val doneButton = findViewById<Button>(R.id.doneButton)
        val backToHubBtn = findViewById<ImageButton>(R.id.backToHubBtn)
        val minLimit = findViewById<EditText>(R.id.minLimit)
        val maxLimit = findViewById<EditText>(R.id.maxLimit)

        // Back to Main Hub button
        backToHubBtn.setOnClickListener {
            // Go back to HubActivity
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Category name change preview
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

        // Upload category image
        uploadImageBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*")) // Pick image using SAF
        }

        // Done button: Save category
        doneButton.setOnClickListener{
            val categoryName = nameInput.text.toString().trim()
            val minLimitStringValue = minLimit.text.toString().trim()           // Retrieve minLimit as a string
            val maxLimitStringValue = maxLimit.text.toString().trim()           // Retrieve minLimit as a string

            when {                                                              // Check category values
                categoryName.isBlank() -> {
                    Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show()
                }
                minLimitStringValue.isBlank() -> {
                    Toast.makeText(this, "Enter min category limit", Toast.LENGTH_SHORT).show()
                }
                maxLimitStringValue.isBlank() -> {
                    Toast.makeText(this, "Enter max category limit", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val categoryMinLimit = minLimitStringValue.toDouble()   // Convert min limit to double
                    val categoryMaxLimit = maxLimitStringValue.toDouble()   // Convert max limit to double

                    // Category instantiation
                    val category = BudgetCategory(                          // Pass Category values for creation
                        name = categoryName,
                        minLimit = categoryMinLimit,
                        maxLimit = categoryMaxLimit,
                        imageUri = imageUri?.toString()
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        val dao = AppDatabase.getInstance(applicationContext).budgetCategoryDao()
                        dao.insertCategory(category)
                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@CreateCategoryActivity, "Category added", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
            }
        }
    }
}