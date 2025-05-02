package com.example.prog7313ui.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Converters to allow Room to handle unsupported types like java.util.Date.
 * Room will auto-generate the implementation at build time.
 * @reference (AndroidDevelopers, 2021).
 */
class Converters {
    /**
     * Converts a Long to a java.util.Date.
     * @param value The Long to convert.
     * @return The java.util.Date representation of the Long.
     * @reference (AndroidDevelopers, 2021).
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? { // Convert from Long to java.util.Date
        return value?.let { Date(it) } // Convert back to java.util.Date
    }

    /**
     * Converts a java.util.Date to a Long.
     * @param date The java.util.Date to convert.
     * @return The Long representation of the date.
     * @reference (AndroidDevelopers, 2021).
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? { // Convert from java.util.Date to Long
        return date?.time // Convert back to Long for storage
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
