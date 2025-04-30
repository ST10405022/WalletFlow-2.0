package com.example.prog7313ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collectLatest
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

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.expenseRecyclerView)
        expenseAdapter = ExpenseAdapter(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = expenseAdapter

        // Setup Date pickers
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

        findViewById<Button>(R.id.applyDateFilterBtn).setOnClickListener {
            applyDateFilter()
        }

        observeExpenses() // Observe changes in expenses and update the list
    }



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

    private fun applyDateFilter() {
        // Apply date filter using the selected start and end date
        observeExpenses(startDate, endDate)
    }

    /**
     * Observe changes in expenses and update the list accordingly.
     * This function is called in the onCreate method.
     */
    private fun observeExpenses(startDate: Date? = null, endDate: Date? = null) {
        lifecycleScope.launch {
            expenseViewModel.getExpensesBetweenDates(startDate, endDate).collectLatest { expenses ->
                expenseAdapter.submitList(expenses)
            }
        }
    }

    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val dateFormat = java.text.SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())
            dateFormat.format(date)
        } else {
            "-"
        }
    }

    private fun formatTime(date: Date?): String {
        return if (date != null) {
            val timeFormat = java.text.SimpleDateFormat("HH:mm", Locale.getDefault())
            timeFormat.format(date)
        } else {
            "-"
        }
    }
}
