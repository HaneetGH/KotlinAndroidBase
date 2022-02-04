package com.technorapper.boiler.data.tunnel.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.technorapper.boiler.data.tunnel.database.dao.UserMasterDao
import com.technorapper.boiler.data.models.UserTableModel
import com.technorapper.boiler.data.utils.RoomConverters

@Database(
    entities = arrayOf(UserTableModel::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RoomConverters::class
)
abstract class exampleDatabase : RoomDatabase() {
    //  private static TechnorapperDatabase TechnorapperDB;

    abstract fun getUserMasterDao(): UserMasterDao

}