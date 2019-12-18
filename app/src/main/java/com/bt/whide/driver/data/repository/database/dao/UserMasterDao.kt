package com.bt.whide.driver.data.repository.database.dao

import androidx.room.*
import com.bt.whide.driver.data.repository.models.UserTableModel
import io.reactivex.Single

@Dao
abstract class UserMasterDao {

    @Query("SELECT * FROM userMaster")
    abstract fun getAll(): List<UserTableModel>



}