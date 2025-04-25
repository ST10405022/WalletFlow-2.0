package com.example.prog7313ui.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Represents a financial expense recorded by the user.
 *
 * Each expense includes details such as:
 * - The amount spent
 * - The date(s) it applies to
 * - A description
 * - The category it belongs to
 * - An optional photo for receipts or visual context
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Unique identifier for each expense
    val amount: Double, // Monetary value
    val date: Date, // Date of expense
    val startDate: Date? = null, // Optional start date for recurring expenses
    val endDate: Date? = null, // Optional end date for recurring expenses
    val description: String, // Optional description of expense
    val categoryId: Int, // Reference to the category the expense belongs to
    val photoPath: String? = null // Optional path to a photo of the expense
)
