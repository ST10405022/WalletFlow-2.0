package com.example.prog7313ui

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Activity to display a list of expenses from the database.
 */
class ExpenseListActivity : AppCompatActivity() {

    private val expenseViewModel: ExpenseViewModel by viewModels() // ViewModel instance for expenses
    private var startDate: Date? = null // Variable to store start date
    private var endDate: Date? = null // Variable to store end date
    private lateinit var expenseAdapter: ExpenseAdapter // Adapter for the expense list

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        // Setup RecyclerView and adapter for displaying expenses (Android, 2025)
        val recyclerView = findViewById<RecyclerView>(R.id.expenseRecyclerView)
        expenseAdapter = ExpenseAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // Setup Date pickers and buttons to select start and end dates (Android, 2025).
        findViewById<Button>(R.id.selectStartDateBtn).setOnClickListener {
            showDatePickerDialog { year, month, day ->
                val calendar = Calendar.getInstance().apply {
                    set(year, month, day, 0, 0, 0)
                }
                startDate = calendar.time // Update the start date
                Toast.makeText(this, "Start Date: ${formatDate(calendar.time)}", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.selectEndDateBtn).setOnClickListener {
            showDatePickerDialog { year, month, day ->
                val calendar = Calendar.getInstance().apply {
                    set(year, month, day, 23, 59, 59)
                }
                endDate = calendar.time // Update the end date
                Toast.makeText(this, "End Date: ${formatDate(calendar.time)}", Toast.LENGTH_SHORT).show()
            }
        }

        // Setup Apply Date Filter button
        findViewById<Button>(R.id.applyDateFilterBtn).setOnClickListener {
            applyDateFilter()
        }

        // Back to HubActivity
        findViewById<ImageButton>(R.id.backToHubBtn).setOnClickListener {
            val intent = Intent(this, HubActivity::class.java)
            startActivity(intent)
            finish()
        }

        observeExpenses() // Observe changes in expenses and update the list
    }

    /**
     * Show a date picker dialog.
     * It takes three parameters: year, month, and day.
     * This function will be called when the user selects a date.
     * @param onDateSet Callback function to handle the selected date.
     * @reference (AndroidDevelopers, 2021).
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

    /**
     * Apply the date filter to the list of expenses.
     * This function is called when the user clicks the "Apply Date Filter" button.
     * @reference (AndroidDevelopers, 2021).
     */
    private fun applyDateFilter() {
        // Apply date filter using the selected start and end date
        observeExpenses(startDate, endDate)
    }

    /**
     * Observe changes in expenses and update the list accordingly.
     * This function is called in the onCreate method.
     * @param startDate The start date for filtering expenses.
     * @param endDate The end date for filtering expenses.
     * @reference (AndroidDevelopers, 2021).
     */
    private fun observeExpenses(startDate: Date? = null, endDate: Date? = null) {
        lifecycleScope.launch {
            expenseViewModel.getExpensesBetweenDates(startDate, endDate).collect { expenses ->
                expenseAdapter.submitList(expenses)
            }
        }
    }


    /**
     * Format a date to a readable string.
     * @param date The date to format.
     * @return A formatted string representation of the date.
     * @reference (AndroidDevelopers, 2021).
     */
    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val dateFormat = java.text.SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            dateFormat.format(date)
        } else {
            "-"
        }
    }
}

/*
 * Reference List
 *     AndroidDevelopers, 2021. Save data in a local database using Room. [Online]
 *     Available at: https://developer.android.com/training/data-storage/room
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. Kotlin coroutines and lifecycle. [Online]
 *     Available at: https://developer.android.com/topic/libraries/architecture/coroutines
 *     [Accessed 22 April 2025].
 *     AndroidDevelopers, 2021. CardView. [Online]
 *     Available at: https://developer.android.com/reference/androidx/cardview/widget/CardView
 *     [Accessed 25 April 2025].
 *     AndroidDevelopers, 2021. View binding. [Online]
 *     Available at: https://developer.android.com/topic/libraries/view-binding
 *     [Accessed 23 April 2025].
 *     AndroidDevelopers, 2021. AlertDialog. [Online]
 *     Available at: https://developer.android.com/reference/androidx/appcompat/app/AlertDialog
 *     [Accessed 24 April 2025].
 *     AndroidDevelopers, 2021. SimpleDateFormat. [Online]
 *     Available at: https://developer.android.com/reference/java/text/SimpleDateFormat
 *     [Accessed 23 April 2025].
 *     MikeT, 2022. stackOverflow. [Online]
 *     Available at: https://stackoverflow.com/questions/74477964/android-studio-add-a-database
 *     [Accessed 28 April 2025].
 *     Android. 2025. Create dynamic lists with RecyclerView:   views:   Android developers,
 *     Android Developers. [Online].
 *     Available at: https://developer.android.com/develop/ui/views/layout/recyclerview
 *     [Accessed: 15 April 2025].
 */