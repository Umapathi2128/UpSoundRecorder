package com.uma.upsoundrecorder.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Umapathi on 05/02/19.
 * Copyright Indyzen Inc, @2019
 */
@Database(entities = [(AudioDataModel::class)],version = 2)
abstract class AudioListDatabase : RoomDatabase() {

    abstract fun audioListDao() : AudioListDao
}