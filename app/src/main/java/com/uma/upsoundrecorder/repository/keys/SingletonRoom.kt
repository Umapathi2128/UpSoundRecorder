package com.uma.upsoundrecorder.repository.keys

import android.content.Context
import androidx.room.Room
import com.uma.upsoundrecorder.repository.database.AudioListDatabase

/**
 * Created by Umapathi on 08/11/18.
 * Copyright Indyzen Inc, @2018
 */
class SingletonRoom(var ctx: Context) {
    var audioListDatabase: AudioListDatabase?=null

    fun singleTonRoomData():AudioListDatabase {
        return if (audioListDatabase==null) {
            audioListDatabase = Room.databaseBuilder(ctx, AudioListDatabase::class.java,  DataKeys.audioListKey)
                    .allowMainThreadQueries().build()
            audioListDatabase!!
        }
        else audioListDatabase!!
    }
}

