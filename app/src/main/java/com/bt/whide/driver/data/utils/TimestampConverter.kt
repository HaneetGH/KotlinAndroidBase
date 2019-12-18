package com.bt.whide.driver.data.utils

import androidx.room.TypeConverter
import com.bt.whide.driver.constants.GlobalVariables
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimestampConverter {
    var df: DateFormat = SimpleDateFormat(GlobalVariables.TIME_STAMP_FORMAT)
    @TypeConverter
    fun fromTimestamp(value: String?): Date? {
        return if (value != null) {
            try {
                return df.parse(value)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            null
        } else {
            null
        }
    }
}
