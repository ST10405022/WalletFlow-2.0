package com.example.prog7313ui.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a category under which expenses can be grouped.
 *
 * Examples include categories like 'Food', 'Transport', or 'Utilities'.
 * Each category can have:
 * - A minimum monthly goal (e.g., spend at least R500 on food).
 * - A maximum monthly limit (e.g., spend no more than R2000 on entertainment).
 * These limits are used to provide financial insights and warnings during expense entry.
 */
@Entity(tableName = "budget_categories")
data class BudgetCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Unique identifier for each category
    val name: String, // Name of the category, e.g., 'Food', 'Transport', etc.
    val minLimit: Double = 0.0, // The minimum amount the user aims to spend monthly in this category. Default is 0.
    val maxLimit: Double = 0.0 // The maximum amount the user aims to spend monthly in this category. Default is 0.
)
