package com.uma.upsoundrecorder.ui.recordfragment

/**
 * Created by Umapathi on 31/01/19.
 * Copyright Indyzen Inc, @2019
 */
class RecordFragmentVM(var recordFragmentView: RecordFragmentView) {

    fun startRecording()
    {
        recordFragmentView.startRecording()
    }

    fun stopRecording()
    {
        recordFragmentView.stopRecording()
    }
}