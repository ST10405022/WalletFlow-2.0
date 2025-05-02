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
 * @reference (AndroidDevelopers, 2021).
 */
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // Unique identifier for each expense
    val amount: Double, // Monetary value of expense
    val date: Date, // Date of expense
    val startDate: Date? = null, // Optional start date for recurring expenses
    val endDate: Date? = null, // Optional end date for recurring expenses
    val description: String, // Optional description of expense
    val categoryId: Int, // Reference to the category the expense belongs to
    val photoPath: String? = null // Optional path to a photo of the expense
)

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
