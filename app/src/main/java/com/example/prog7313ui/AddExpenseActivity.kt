package com.example.prog7313ui

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.BudgetCategory
import com.example.prog7313ui.data.entity.Expense
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Activity for adding a new expense to the database.
*/
class AddExpenseActivity : AppCompatActivity() {
    // UI elements
    private lateinit var nameInput: EditText // EditText for name
    private lateinit var amountInput: EditText // EditText for amount
    private lateinit var dateInput: Button // Button for date
    private lateinit var descInput: EditText // EditText for description
    private lateinit var createExpenseButton: Button // Button to create the expense
    private lateinit var backButton: ImageButton // Button to go back to HubActivity
    private lateinit var startDateInput: Button // Button for start date
    private lateinit var endDateInput: Button // Button for end date
    private lateinit var previewImage: ImageView // ImageView to preview the selected image
    private lateinit var uploadPhotoBtn: Button // Button to upload receipt image
    private lateinit var recurringExpenseCheckBox: CheckBox // Checkbox for recurring expenses
    private lateinit var categorySpinner: Spinner // Spinner for selecting a category

    // SimpleDateFormat for parsing the date input
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    private var selectedImageUri: Uri? = null // URI for the selected image

    // Explicit launcher for picking an image using the MediaStore (via SAF)
    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        categorySpinner = findViewById(R.id.categorySpinner)

        loadCategoriesIntoSpinner() // Load categories from the database and populate the spinner

        // Bind views to variables
        nameInput = findViewById(R.id.inputExpenseName)
        amountInput = findViewById(R.id.inputAmount)
        dateInput = findViewById(R.id.inputDate)
        descInput = findViewById(R.id.inputDescription)
        categorySpinner = findViewById(R.id.categorySpinner)
        createExpenseButton = findViewById(R.id.createExpenseBtn)
        backButton = findViewById(R.id.backToHubBtn)
        recurringExpenseCheckBox = findViewById(R.id.recurringExpenseCheckBox)
        startDateInput = findViewById(R.id.inputStartDate)
        endDateInput = findViewById(R.id.inputEndDate)
        uploadPhotoBtn = findViewById(R.id.uploadReceiptBtn)
        previewImage = findViewById(R.id.previewImage)

        // Set up date picker for Date input
        dateInput.setOnClickListener {
            showDatePickerDialog { year, month, day ->
                val formatted = "%02d/%02d/%04d".format(day, month + 1, year)
                dateInput.text = formatted
            }
        }

        // Set up date picker for Start Date (only visible if recurring)
        startDateInput.setOnClickListener {
            showDatePickerDialog { year, month, day ->
                val formatted = "%02d/%02d/%04d".format(day, month + 1, year)
                startDateInput.text = formatted
            }
        }

        // Set up date picker for End Date (only visible if recurring)
        endDateInput.setOnClickListener {
            showDatePickerDialog { year, month, day ->
                val formatted = "%02d/%02d/%04d".format(day, month + 1, year)
                endDateInput.text = formatted
            }
        }

        // Back to HubActivity
        backButton.setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Toggle visibility of start/end dates based on recurring checkbox
        recurringExpenseCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                startDateInput.visibility = View.VISIBLE
                endDateInput.visibility = View.VISIBLE
            } else {
                startDateInput.visibility = View.GONE
                endDateInput.visibility = View.GONE
            }
        }

        // Handle expense creation with validation and RoomDB insert
        createExpenseButton.setOnClickListener {
            if (validateInputs()) {
                saveExpenseToDatabase()
            }
        }

        // Upload Receipt Image
        uploadPhotoBtn.setOnClickListener {
            pickImageLauncher.launch(arrayOf("image/*")) // Pick image using SAF
        }
    }

    /**
     * Load all categories from the database and populate the spinner.
     * This is done in a background thread using coroutines.
     * @return A list of BudgetCategory objects.
     */
    private fun loadCategoriesIntoSpinner() {
        lifecycleScope.launch {
            try {
                // Get the database instance
                val db = AppDatabase.getInstance(this@AddExpenseActivity)
                val categoryDao = db.budgetCategoryDao()

                // Load all categories from the database in a background thread
                categoryDao.getAllCategories().collect { categories ->
                    val adapter = ArrayAdapter(
                        this@AddExpenseActivity,
                        android.R.layout.simple_spinner_item,
                        categories // pass the full objects, not strings
                    )
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    categorySpinner.adapter = adapter
                }

            } catch (e: Exception) { // Handle exceptions if needed
                Toast.makeText(this@AddExpenseActivity, "Failed to load categories: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Validate all required inputs and show inline errors if needed.
     * @return True if all fields are valid, false otherwise.
     */
    private fun validateInputs(): Boolean {
        var valid = true // Assume all fields are valid

        // Validate name input
        if (nameInput.text.isNullOrBlank()) { // Check if name is empty
            nameInput.error = "Name is required" // Set error message if empty
            valid = false // Set to false if any field is invalid
        }

        // Validate amount input
        if (amountInput.text.isNullOrBlank()) { // Check if amount is empty
            amountInput.error = "Amount is required" // Set error message if empty
            valid = false // Set to false if any field is invalid
        } else {
            try {
                amountInput.text.toString().toDouble() // Convert to double for validation
            } catch (e: NumberFormatException) { // Handle non-numeric input
                amountInput.error = "Invalid amount format" // Set error message if invalid
                valid = false // Set to false if any field is invalid
            }
        }

        // Validate date input
        if (dateInput.text.isNullOrBlank()) { // Check if date is empty
            dateInput.error = "Date is required" // Set error message if empty
            valid = false // Set to false if any field is invalid
        }

        // Validate description input
        if (descInput.text.isNullOrBlank()) { // Check if description is empty
            descInput.error = "Description is required" // Set error message if empty
            valid = false // Set to false if any field is invalid
        }

        // Validate category selection
        if (categorySpinner.selectedItem == null ||
            categorySpinner.selectedItem.toString().isEmpty()) { // Check if category is selected
            Toast.makeText(this,
                "Please select a category", Toast.LENGTH_SHORT).show() // Inform user
            valid = false // Set to false if any field is invalid
        }

        // Validate start and end dates if the expense is recurring
        if (recurringExpenseCheckBox.isChecked) {
            if (startDateInput.text.isNullOrBlank()) {
                startDateInput.error = "Start date is required for recurring expenses"
                valid = false
            }
            if (endDateInput.text.isNullOrBlank()) {
                endDateInput.error = "End date is required for recurring expenses"
                valid = false
            }
        }

        return valid // Return true if all fields are valid
    }

    /**
     * Insert validated expense into RoomDB.
     */
    private fun saveExpenseToDatabase() {
        // Parse date input
        val parsedDate = try { // Attempt to parse the date
            dateFormat.parse(dateInput.text.toString().trim()) // Trim leading/trailing spaces
        } catch (e: Exception) { // Handle invalid date format
            Toast.makeText(this, "Invalid date format. Use YYYY-MM-DD.",
                Toast.LENGTH_LONG).show() // Inform user of invalid format
            return // Exit function if date is invalid
        }

        // Parse start and end dates if recurring
        val startDate: Date? = if (recurringExpenseCheckBox.isChecked && !startDateInput.text.isNullOrBlank()) {
            parseDate(startDateInput.text.toString())
        } else { null }
        val endDate: Date? = if (recurringExpenseCheckBox.isChecked && !endDateInput.text.isNullOrBlank()) {
            parseDate(endDateInput.text.toString())
        } else { null }

        // Convert category ID from spinner (assuming it's mapped to BudgetCategory objects)
        val selectedCategory = categorySpinner.selectedItem as? BudgetCategory // Cast to BudgetCategory
        if (selectedCategory == null) { // Handle invalid category selection
            Toast.makeText(this, "Invalid category selected.",
                Toast.LENGTH_SHORT).show() // Inform user of invalid selection
            return // Exit function if category is invalid
        }

        // Create Expense object with parsed date
        val expense = Expense(
            amount = amountInput.text.toString().toDouble(), // Convert to double
            date = parsedDate!!, // Non-null assertion as we checked for validity
            description = descInput.text.toString().trim(), // Trim leading/trailing spaces
            categoryId = selectedCategory.id, // Use the ID from the selected category
            photoPath = selectedImageUri?.toString(), // Convert to string for storage
            startDate = startDate, // insert startDate if recurring
            endDate = endDate // insert endDate if recurring
        )
        Log.d("ExpensePhotoPath", "Saving photo at path: ${expense.photoPath}")

        lifecycleScope.launch {
            try {
                val expenseDao = AppDatabase.getInstance(applicationContext).expenseDao()
                withContext(Dispatchers.IO) {
                    expenseDao.insertExpense(expense) // Insert expense into database
                }
                Toast.makeText(this@AddExpenseActivity,
                    "Expense saved!", Toast.LENGTH_SHORT).show() // Inform user of success
                finish() // Optional: go back after saving
            }
            catch (e: Exception) { // Handle exceptions if needed
                Toast.makeText(this@AddExpenseActivity,
                    "Error saving expense: ${e.localizedMessage}",
                    Toast.LENGTH_LONG).show() // Inform user of failure
            }
        }
    }

    /**
     * Convert date string to Date object using SimpleDateFormat.
     * @param dateString The date string to parse.
     * @return The parsed Date object or null if parsing fails.
     */
    private fun parseDate(dateString: String): Date? {
        return try {
            dateFormat.parse(dateString) // Parse the date string into a Date object
        } catch (e: Exception) { // Handle exceptions if needed
            null // Return null if parsing fails
        }
    }

    /**
     * Show a DatePickerDialog to select a date.
     * @param onDateSet A callback function to handle the selected date.
     * It takes three parameters: year, month, and day.
     * These parameters represent the selected date.
     */
    private fun showDatePickerDialog(onDateSet: (Int, Int, Int) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth -> onDateSet(year, month, dayOfMonth) },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}