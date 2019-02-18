package com.uma.upsoundrecorder.repository.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Observable

/**
 * Created by Umapathi on 05/02/19.
 * Copyright Indyzen Inc, @2019
 */
@Dao
interface AudioListDao {

    @Insert
    fun insertAudioList(audioDataModel: AudioDataModel)

    @Query("SELECT * FROM AudioDataModel")
    fun getAudioList(): Observable<AudioDataModel>

    @Query("SELECT * FROM AudioDataModel")
    fun getAudioList2(): MutableList<AudioDataModel>

    @Query("UPDATE AudioDataModel SET title=:newValue WHERE title=:title")
    fun updateTitle(title: String,newValue:String)

    @Query("DELETE FROM AudioDataModel WHERE title=:title")
    fun deleteAudio(title: String)
}