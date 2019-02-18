package com.uma.upsoundrecorder.ui.recordfragment


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.datamanager.AppDataManager
import com.uma.upsoundrecorder.repository.database.AudioDataModel
import com.uma.upsoundrecorder.ui.mainactivity.MainActivity
import kotlinx.android.synthetic.main.fragment_record.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


/**
 *Created by Umapathi on 31/01/19.
 * Copyright Indyzen Inc, @2019
 */
class RecordFragment : Fragment(), RecordFragmentView {


    lateinit var recordBinding: com.uma.upsoundrecorder.databinding.FragmentRecordBinding
    private val MY_PERMISSIONS_RECORD_AUDIO = 1
    private var mFileName: String? = null
    private var mFilePath: String? = null
    private lateinit var mAppDataManager: AppDataManager
    var currentDate: String? = null

    var mRecorder: MediaRecorder? = null

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using dataBinding..

        recordBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_record, container, false)
        recordBinding.recordFragmentData = RecordFragmentVM(this)

        val directory=File(Environment.getExternalStorageDirectory(),"SoundRecorder")
        if (!directory.exists())
        {
            directory.mkdir()
        }
        // intilizing the AppDataManager class
        mAppDataManager = AppDataManager(context!!)
//        requestAudioPermissions()

//        val file = Environment.getDataDirectory()
//
//        try {
//            audioFile = File.createTempFile("Sound", ".3gp", file)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
        return recordBinding.root
    }

    /**
     * This method for set the path to store our recorded data into sdcard....
     */
    private fun setFileNameAndPath() {
        var count = 0
        var f: File
        do {
            count++
            val time = System.currentTimeMillis()
            currentDate = convertMillsectoDate(time)
            mFileName = "Sound_$time.mp4"
            mFilePath = Environment.getExternalStorageDirectory().absolutePath
            mFilePath += "/SoundRecorder/$mFileName"

            f = File(mFilePath)
        } while (f.exists() && !f.isDirectory)
    }

    /**
     * This method for convert milliseconds to current date....
     */
    @SuppressLint("SimpleDateFormat")
    private fun convertMillsectoDate(millisec: Long): String {
        val simple = SimpleDateFormat("dd MMM yyyy HH:mm a")
        val resultDate = Date(millisec)
        return simple.format(resultDate)
    }

    /**
     *  This method for start the media player save it into sdcard....
     */
    override fun startRecording() {
//        requestAudioPermissions()
//        requestPermission2()
        txtRecord.text=resources.getString(R.string.reording)
        setFileNameAndPath()
        mRecorder = MediaRecorder()
        mRecorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        mRecorder!!.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mRecorder!!.setOutputFile(mFilePath)
        mRecorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mRecorder!!.setAudioChannels(1)
//        mediaRecorder= MediaRecorder()
//        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
//        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
//        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
//        mediaRecorder.setOutputFile(audioFile?.absoluteFile)
        mRecorder!!.prepare()
        if (fabStart.visibility == View.VISIBLE) {
            mRecorder!!.start()
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
            fabStart.visibility = View.GONE
            fabStop.visibility = View.VISIBLE
        }
    }

    /**
     * This method for stop the media player and local database...
     */
    override fun stopRecording() {
        txtRecord.text=resources.getString(R.string.tap_button)
        if (fabStop.visibility == View.VISIBLE) {
            mRecorder!!.stop()
            mRecorder!!.release()
            mRecorder = null
            Toast.makeText(activity, chronometer.text, Toast.LENGTH_LONG).show()
            saveAudioToSdcard(chronometer.text.toString())
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.stop()
            fabStart.visibility = View.VISIBLE
            fabStop.visibility = View.GONE

            (activity as MainActivity).addData()
        }
    }

    /**
     * This method for saving audio details to database....
     */
    @SuppressLint("CheckResult")
    private fun saveAudioToSdcard(duration: String) {
        val audioList = ArrayList<AudioDataModel>()
        mAppDataManager.singletonRoom.singleTonRoomData().audioListDao()
            .insertAudioList(AudioDataModel(mFileName!!, duration, currentDate!!,mFilePath!!))

        mAppDataManager.getAudioList().subscribe {
            audioList.add(AudioDataModel(it.title, it.duration, it.time,it.path))
            Log.e("DataBase DAta", "" + it.title)
        }

//        val audioList = ArrayList<AudioDataModel>()
//        for (i in 0 until list.size) {
//            audioList.add(AudioDataModel(list[i].title, list[i].path, list[i].duration))
//            Log.e("DataBase DAta", "" + list[i].title)
//        }
    }

//    fun requestPermission2() {
//        val permission = ContextCompat.checkSelfPermission(
//            context!!,
//            Manifest.permission.RECORD_AUDIO
//        )
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(arrayOf(Manifest.permission.RECORD_AUDIO), 1)
//        } else {
//            Toast.makeText(context, "Please grant permissions to record audio", Toast.LENGTH_LONG).show()
//        }
//
//    }
//
//    private fun requestAudioPermissions() {
//        if (ContextCompat.checkSelfPermission(
//                context!!,
//                Manifest.permission.RECORD_AUDIO
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//
//            //When permission is not granted by user, show them message why this permission is needed.
//            if (ActivityCompat.shouldShowRequestPermissionRationale(
//                    context as Activity,
//                    Manifest.permission.RECORD_AUDIO
//                )
//            ) {
//                Toast.makeText(context, "Please grant permissions to record audio", Toast.LENGTH_LONG).show()
//
//                //Give user option to still opt-in the permissions
//                requestPermissions(
//                    arrayOf(Manifest.permission.RECORD_AUDIO),
//                    MY_PERMISSIONS_RECORD_AUDIO
//                )
//
//            } else {
//                // Show user dialog to grant permission to record audio
//                requestPermissions(
//                    arrayOf(Manifest.permission.RECORD_AUDIO),
//                    MY_PERMISSIONS_RECORD_AUDIO
//                )
//            }
//        } else if (ContextCompat.checkSelfPermission(
//                context!!,
//                Manifest.permission.RECORD_AUDIO
//            ) == PackageManager.PERMISSION_GRANTED
//        ) {
//
//
//        }
//    }
}
