package com.bt.whide.driver.data.tunnel.database.dao

import androidx.room.*
import com.bt.whide.driver.data.models.UserTableModel

@Dao
abstract class UserMasterDao {

    @Query("SELECT * FROM userMaster")
    abstract fun getAll(): List<UserTableModel>



}