package com.uma.upsoundrecorder.ui.changeaudio

/**
 * Created by Umapathi on 13/02/19.
 * Copyright Indyzen Inc, @2019
 */
class ChangeAudioDetailsVM(var changeAudioDetailsView: ChangeAudioDetailsView) {

    fun renameAudioFile() {
        changeAudioDetailsView.renameAudioFile()
    }

    fun shareAudioFile() {
        changeAudioDetailsView.shareAudioFile()
    }

    fun deleteAudioFile() {
        changeAudioDetailsView.deleteAudioFile()
    }

    fun cancelClick() {
        changeAudioDetailsView.onCancelClick()
    }

    fun renameFileButtonClick() {
        changeAudioDetailsView.renameFileButtonClick()
    }
}