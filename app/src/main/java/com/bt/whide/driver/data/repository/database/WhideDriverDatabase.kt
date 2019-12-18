package com.bt.whide.driver.data.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bt.whide.driver.data.repository.database.dao.UserMasterDao
import com.bt.whide.driver.data.repository.models.UserTableModel
import com.bt.whide.driver.data.repository.utils.RoomConverters

@Database(
    entities = arrayOf(UserTableModel::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(
    RoomConverters::class
)
abstract class WhideDatabase : RoomDatabase() {
    //  private static LetstrackDatabase letstrackDB;

    abstract fun getUserMasterDao(): UserMasterDao

}