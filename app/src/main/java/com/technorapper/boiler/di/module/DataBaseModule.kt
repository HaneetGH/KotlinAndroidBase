package com.technorapper.boiler.di.module

import androidx.room.Room
import com.technorapper.boiler.constants.DatabaseConstants
import com.technorapper.boiler.data.tunnel.database.exampleDatabase
import com.technorapper.boiler.data.tunnel.database.dao.UserMasterDao

import com.technorapper.boiler.application.App
import com.technorapper.boiler.di.scopes.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {


    @Provides
    @ApplicationScoped
    fun provideWDDatabase(context: App): exampleDatabase {
        return Room.databaseBuilder(
            context,
            exampleDatabase::class.java, DatabaseConstants.DB_NAME
        ).fallbackToDestructiveMigration() /*.allowMainThreadQueries()*/
            .build()
    }


    @ApplicationScoped
    @Provides

    fun provideUserMasterDao(exampleDatabase: exampleDatabase): UserMasterDao {
        return exampleDatabase.getUserMasterDao()
    }


}