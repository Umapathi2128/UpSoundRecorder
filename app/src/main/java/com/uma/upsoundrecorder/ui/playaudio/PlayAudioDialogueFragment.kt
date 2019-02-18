package com.uma.upsoundrecorder.ui.playaudio

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.databinding.CustomPlayDialogeBinding
import kotlinx.android.synthetic.main.custom_play_dialoge.*
import java.io.File


/**
 * Created by Umapathi on 12/02/19.
 * Copyright Indyzen Inc, @2019
 */
class PlayAudioDialogueFragment : DialogFragment(), PlayAudioDialogueView {


    var isPlaying = true
    lateinit var playDialogeBinding: CustomPlayDialogeBinding

    lateinit var mp: MediaPlayer
    lateinit var handler: Handler
    var durationHandler = Handler()
    var duration:String?=null
    var path:String?=null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val bundles = arguments
        handler = Handler()

        var title: String? = ""
        if (arguments != null) {
            path = bundles?.getString("path")
            title = bundles?.getString("title")
            duration = bundles?.getString("duration")
        }
        playDialogeBinding =
            DataBindingUtil.inflate(inflater, R.layout.custom_play_dialoge, container, false)
        val playAudioDialogueVM = PlayAudioDialogueVM(this, title!!, duration!!, path!!)
        playDialogeBinding.playDialoge = playAudioDialogueVM
        return playDialogeBinding.root
    }

    @SuppressLint("NewApi")
    override fun playRpauseAudio() {

        isPlaying = if (isPlaying) {
//            mp.prepareAsync()
            mp = MediaPlayer.create(context, Uri.fromFile(File(path)))

            playDialogeBinding.seekbar.max = mp.duration/1000

            mp.start()
            updateSeekbar()

            fab_play_dialogue.setImageResource(R.drawable.ic_stop)
            false
        } else {
            mp.stop()
            fab_play_dialogue.setImageResource(R.drawable.ic_play)
            true
        }
    }


    private fun updateSeekbar() {

        playDialogeBinding.seekbar.max=mp.duration/1000
        playDialogeBinding.seekbar.progress = mp.currentPosition / 1000
        current_progress_text_view.text = (mp.currentPosition / 1000).toString()
        durationHandler.postDelayed(mTicker, 100)

//        activity?.runOnUiThread {
//            seekbar.progress=mp.currentPosition/1000
//            current_progress_text_view.text=(mp.currentPosition/1000).toString()
//            handler.postDelayed( this,100)
//        }
    }


    var mTicker: Runnable = object : Runnable {
        override fun run() {

            playDialogeBinding.seekbar.progress = mp.currentPosition / 1000
            current_progress_text_view.text = (mp.currentPosition / 1000).toString()

            if (mp.currentPosition/1000<(mp.duration/1000))
            {

//                durationHandler.removeMessages(0)
//                dialog?.dismiss()
                durationHandler.postDelayed(this, 1000)
            }else{
                fab_play_dialogue.setImageResource(R.drawable.ic_play)
                mp.stop()
                isPlaying=true
                current_progress_text_view.text="0.0"
                seekbar.progress=0
//                mp.release()
            }
            // user interface interactions and updates on screen
            // if you want to run this handler only once then delete below line
        }
    }

//    var handlers:Runnable= Runnable {
//
//        durationHandler.postDelayed(handlers,100)
//    }

    override fun onDestroy() {
        super.onDestroy()
//        if (mp!=null) mp.stop()
//        mp.release()
        durationHandler.removeCallbacks(mTicker)
    }
}

