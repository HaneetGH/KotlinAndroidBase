package com.bt.whide.driver.data.repository.utils

import androidx.room.TypeConverter
import java.util.*

class  RoomConverters {
    @TypeConverter

    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
}
