package com.bt.whide.driver.di.module

import androidx.room.Room
import com.bt.whide.driver.constants.DatabaseConstants
import com.bt.whide.driver.data.tunnel.database.WhideDatabase
import com.bt.whide.driver.data.tunnel.database.dao.UserMasterDao
import com.bt.whide.driver.application.App
import com.bt.whide.driver.di.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {


/*    @ApplicationScoped
    @Provides
    fun provideWDDatabase(context: Context?): WhideDatabase? {
        return Room.databaseBuilder(
            context!!.applicationContext,
            WhideDatabase::class.java, DatabaseConstants.DB_NAME
        )
            .fallbackToDestructiveMigration() *//*.allowMainThreadQueries()*//*
            .build()
    }*/

    @Provides
    @ApplicationScoped
    fun provideWDDatabase(context: App): WhideDatabase {
        return Room.databaseBuilder(
            context,
            WhideDatabase::class.java, DatabaseConstants.DB_NAME
        ).fallbackToDestructiveMigration() /*.allowMainThreadQueries()*/
            .build()
    }


    @ApplicationScoped
    @Provides

    fun provideUserMasterDao(whideDatabase: WhideDatabase): UserMasterDao {
        return whideDatabase.getUserMasterDao()
    }


}