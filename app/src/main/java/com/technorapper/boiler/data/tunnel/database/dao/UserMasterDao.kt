package com.technorapper.boiler.data.tunnel.database.dao

import androidx.room.*
import com.technorapper.boiler.data.models.UserTableModel

@Dao
abstract class UserMasterDao {

    @Query("SELECT * FROM userMaster")
    abstract fun getAll(): List<UserTableModel>



}