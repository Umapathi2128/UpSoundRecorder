package com.uma.upsoundrecorder.ui.changeaudio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.google.android.material.snackbar.Snackbar
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.databinding.ChangeAudioDetailsFragmentBinding
import com.uma.upsoundrecorder.datamanager.AppDataManager
import com.uma.upsoundrecorder.ui.mainactivity.MainActivity
import kotlinx.android.synthetic.main.change_audio_details_fragment.*
import java.io.File


/**
 * Created by Umapathi on 13/02/19.
 * Copyright Indyzen Inc, @2019
 */
class ChangeAudioDetailsFragment : DialogFragment(), ChangeAudioDetailsView {


    lateinit var path: String
    lateinit var title: String
    lateinit var changeAudioDetailsFragmentBinding: ChangeAudioDetailsFragmentBinding
    lateinit var mAppDataManager: AppDataManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        changeAudioDetailsFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.change_audio_details_fragment, container, false
        )
        val changeAudioDetailsVM = ChangeAudioDetailsVM(this)
        changeAudioDetailsFragmentBinding.changeFragment = changeAudioDetailsVM

        // function calling....
        getDetailsFromBundle()
        mAppDataManager = AppDataManager(context!!)
        return changeAudioDetailsFragmentBinding.root
    }


    /**
     * This method for getting data from bundle....
     */
    private fun getDetailsFromBundle() {
        val bundle = arguments
        if (bundle != null) {
            path = bundle.getString("path")!!
            title = bundle.getString("title")!!
            Log.e("Data Path", path)
        } else path = ""
    }

    override fun onCancelClick() {
//        super.onDestroy()
        dialog?.dismiss()
    }

    override fun shareAudioFile() {

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "audio/*"
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, Uri.fromFile(File(path)))
        startActivity(Intent.createChooser(shareIntent, "Share using"))
        dialog?.dismiss()
    }

    override fun deleteAudioFile() {
        val file = File(path)
        if (file.delete()) Toast.makeText(context, "file Deleted$path", Toast.LENGTH_SHORT).show()
        mAppDataManager.singletonRoom.singleTonRoomData().audioListDao().deleteAudio(title)
        (activity as MainActivity).addData()
        dialog?.dismiss()
    }

    override fun renameAudioFile() {
        ll_rename.visibility = View.VISIBLE
    }


    override fun renameFileButtonClick() {
        val newText = etxt_rename.text
        var isNameMatched = false

        val list = mAppDataManager.singletonRoom.singleTonRoomData().audioListDao().getAudioList2()
        for (i in 0 until list.size) {
            if (newText.trim().toString() == list[i].title) isNameMatched = true
        }

        if (newText != null && !newText.isEmpty()) {

            if (!isNameMatched) {
                val sdcard = Environment.getExternalStorageDirectory().toString() + "/SoundRecorder"

                val fromFile = File(sdcard, title)
                val toFile = File(sdcard, etxt_rename.text.toString()+".mp4")
                if (fromFile.renameTo(toFile)) {
                    Toast.makeText(context, "file Renamed", Toast.LENGTH_SHORT).show()
                }
                mAppDataManager.singletonRoom.singleTonRoomData().audioListDao()
                    .updateTitle(title, etxt_rename.text.toString()+".mp4")
                (activity as MainActivity).addData()
                dialog?.dismiss()
            } else {
                Toast.makeText(context, "Name already exists...", Toast.LENGTH_LONG).show()
            }
        } else {
            Snackbar.make(ll_root_rename, "Please enter text", Snackbar.LENGTH_LONG).show()
        }
    }
}