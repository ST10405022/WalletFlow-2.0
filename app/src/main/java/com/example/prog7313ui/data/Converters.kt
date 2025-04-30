package com.example.prog7313ui.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Converters to allow Room to handle unsupported types like java.util.Date.
 * Room will auto-generate the implementation at build time.
 */
class Converters {
    /**
     * Converts a Long to a java.util.Date.
     * @param value The Long to convert.
     * @return The java.util.Date representation of the Long.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? { // Convert from Long to java.util.Date
        return value?.let { Date(it) } // Convert back to java.util.Date
    }

    /**
     * Converts a java.util.Date to a Long.
     * @param date The java.util.Date to convert.
     * @return The Long representation of the date.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? { // Convert from java.util.Date to Long
        return date?.time // Convert back to Long for storage
    }
}
