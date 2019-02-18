package com.example.yugan.savedata.repository.preference

import com.uma.upsoundrecorder.ui.listrecordings.ListFragmentModel
import javax.crypto.SecretKey


/**
 * Created by CompIndia on 30/01/18.
 */
interface AppPreference {

   fun getSharedAudio() : String

   fun setSharedAudio(listFragmentModel: ListFragmentModel)

//   fun setLockType(value:String)
//
//   fun setNoLock(value:String)
//
//   fun getNoLock():String
//
//   fun setSecretKey(key:String)
//
//   fun getSecretKey():String
}