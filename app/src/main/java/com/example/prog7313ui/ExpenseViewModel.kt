package com.example.prog7313ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.prog7313ui.data.AppDatabase
import com.example.prog7313ui.data.entity.Expense
import kotlinx.coroutines.flow.Flow
import java.util.*

class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    private val expenseDao = AppDatabase.getInstance(application).expenseDao()

    // Flow to observe all expenses
    val allExpenses: Flow<List<Expense>> = expenseDao.getAllExpenses()

    // Get expenses by between dates
    fun getExpensesBetweenDates(startDate: Date?, endDate: Date?): Flow<List<Expense>> {
        return expenseDao.getExpensesBetweenDates(startDate, endDate)
    }
}
