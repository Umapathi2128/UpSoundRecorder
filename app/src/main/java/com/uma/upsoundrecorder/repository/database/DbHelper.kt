package com.uma.upsoundrecorder.repository.database

import io.reactivex.Observable

/**
 * Created by Umapathi on 05/02/19.
 * Copyright Indyzen Inc, @2019
 */
interface DbHelper {

    fun insertAudio(audioDataModel: AudioDataModel)

    fun updateAudioTitle(title: String,newValue:String)

    fun getAudioList(): Observable<AudioDataModel>

    fun getAudioList2(): MutableList<AudioDataModel>

    fun deleteAudio(title: String)
}