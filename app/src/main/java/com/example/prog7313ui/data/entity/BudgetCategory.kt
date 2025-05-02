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
 * @reference (AndroidDevelopers, 2021).
 */
@Entity(tableName = "budget_categories")
data class BudgetCategory(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Unique identifier for each category
    val name: String, // Name of the category, e.g., 'Food', 'Transport', etc.
    val minLimit: Double = 0.0, // The minimum amount the user aims to spend monthly in this category. Default is 0.
    val maxLimit: Double = 0.0, // The maximum amount the user aims to spend monthly in this category. Default is 0.
    val imageUri: String? = null // The URI of an image associated with this category.
){
    override fun toString(): String {
        return "$id. $name"
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