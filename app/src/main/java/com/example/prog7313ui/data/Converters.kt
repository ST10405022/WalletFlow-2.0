package com.example.prog7313ui.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Converters to allow Room to handle unsupported types like java.util.Date.
 */
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? { // Convert from Long to java.util.Date
        return value?.let { Date(it) } // Convert back to java.util.Date
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? { // Convert from java.util.Date to Long
        return date?.time // Convert back to Long
    }
}
