package com.technorapper.boiler.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.technorapper.boiler.data.models.base.BaseDaoModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "userMaster" + "")
data class UserTableModel(
    @SerializedName("userMaster_id")
    @Expose
    @PrimaryKey
    @ColumnInfo(name = "userMaster_id")
    var userMaster_id: String,

    @SerializedName("lat")
    @Expose
    @ColumnInfo(name = "lat")
    val lat: String,

    @SerializedName("lng")
    @Expose
    @ColumnInfo(name = "lng")
    val lng: String,

    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    var name: String
) : BaseDaoModel() {
}