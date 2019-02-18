package com.uma.upsoundrecorder.ui.playaudio

/**
 * Created by Umapathi on 12/02/19.
 * Copyright Indyzen Inc, @2019
 */
class PlayAudioDialogueVM(var playAudioDialogueView: PlayAudioDialogueView,var title:String,var duration:String,var path:String){

    fun playRpauseAudio()
    {
        playAudioDialogueView.playRpauseAudio()
    }
}