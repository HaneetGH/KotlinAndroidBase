package com.bt.whide.driver.data.models.base

import androidx.databinding.BaseObservable
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.bt.whide.driver.data.utils.TimestampConverter
import com.google.gson.annotations.Expose
import java.io.Serializable
import java.util.*

open class BaseDaoModel() : BaseObservable(), Serializable {

    @Expose
    @TypeConverters(TimestampConverter::class)
    @ColumnInfo(name = "createdOn")
    var createdOn = Date()
    @Expose
    @TypeConverters(TimestampConverter::class)
    @ColumnInfo(name = "updatedOn")
    var updatedOn = Date()
    @Expose
    @ColumnInfo(name = "updatedBy")
    var updatedBy = ""

}
