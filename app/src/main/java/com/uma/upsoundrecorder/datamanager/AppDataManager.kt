package com.uma.upsoundrecorder.datamanager

import android.content.Context
import com.example.yugan.savedata.repository.preference.AppPreferenceHelper
import com.example.yugan.savedata.repository.preference.PreferenceKeys
import com.uma.upsoundrecorder.repository.database.AudioDataModel
import com.uma.upsoundrecorder.repository.keys.SingletonRoom
import com.uma.upsoundrecorder.ui.listrecordings.ListFragmentModel
import io.reactivex.Observable

//import rx.Observable


/**
 * Created by Umapathi on 05/02/19.
 * Copyright Indyzen Inc, @2019
 */
class AppDataManager(
    var context: Context, var singletonRoom: SingletonRoom = SingletonRoom(context),
    var mAppPreferenceHelper: AppPreferenceHelper = AppPreferenceHelper(context)
) : AppDataHelper {

    override fun setSharedAudio(listFragmentModel: ListFragmentModel) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getSharedAudio(): String {
        return mAppPreferenceHelper.getString(PreferenceKeys.shareAudio).let { "" }
    }


    override fun getAudioList2(): MutableList<AudioDataModel> {
        return singletonRoom.singleTonRoomData().audioListDao().getAudioList2()
    }


    override fun insertAudio(audioDataModel: AudioDataModel) {
        singletonRoom.audioListDatabase?.audioListDao()?.insertAudioList(audioDataModel)
    }

    override fun updateAudioTitle(title: String,newValue:String) {
        singletonRoom.audioListDatabase?.audioListDao()?.updateTitle(title,newValue)!!
    }

    override fun getAudioList(): Observable<AudioDataModel> {


        return singletonRoom.singleTonRoomData().audioListDao().getAudioList()
    }

    override fun deleteAudio(title: String) {
        singletonRoom.singleTonRoomData().audioListDao().deleteAudio(title)
    }
}