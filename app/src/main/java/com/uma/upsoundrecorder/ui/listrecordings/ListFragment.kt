package com.uma.upsoundrecorder.ui.listrecordings


import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.databinding.FragmentListBinding
import com.uma.upsoundrecorder.datamanager.AppDataManager
import com.uma.upsoundrecorder.ui.changeaudio.ChangeAudioDetailsFragment
import com.uma.upsoundrecorder.ui.playaudio.PlayAudioDialogueFragment
import java.io.File


/**
 *Created by Umapathi on 12/02/19.
 * Copyright Indyzen Inc, @2019
 */
class ListFragment : Fragment(), ListOnClickListener {

    lateinit var listFragmentListBinding: FragmentListBinding
    private lateinit var mAppDataManager: AppDataManager
    lateinit var list: ArrayList<ListFragmentModel>
    lateinit var listAdapter: ListAdapter

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewIntilizing(container!!)
        setAdapter()
//        getListAllFiles()
        return listFragmentListBinding.root
    }

    /**
     * This is used for intilizing all the methods and feilds....
     */
    private fun viewIntilizing(container: ViewGroup) {
        mAppDataManager = AppDataManager(context!!)
        list = ArrayList()

        listFragmentListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_list, container, false)
    }

    /**
     * Here getting all the data from database and seting it into recyclerview adapter.....
     */
    private fun setAdapter() {
        val getList = mAppDataManager.singletonRoom.singleTonRoomData().audioListDao().getAudioList2()
//        Log.e("List size", getList.size.toString())
        for (i in 0 until getList.size) {
            list.add(ListFragmentModel(getList[i].title, getList[i].duration, getList[i].time, getList[i].path))
        }
        listAdapter = ListAdapter(list, this)
        val recyclerView: RecyclerView.LayoutManager = LinearLayoutManager(context)
        listFragmentListBinding.listRecycler.layoutManager = recyclerView
        listFragmentListBinding.listRecycler.adapter = listAdapter
    }

    /**
     * This method is udsed for list all files from sdcard....
     */
    private fun getListAllFiles(): ArrayList<String> {
        val titleList = ArrayList<String>()
        val path = Environment.getExternalStorageDirectory().toString() + "/SoundRecorder"
        Log.d("Files", "Path: $path")
        val directory = File(path)
        val files = directory.listFiles()
        if (files.isNotEmpty()) {
            for (i in files.indices) {
                titleList.add(files[i].name)
//                Log.e("Files", "FileName:" + files[i].name)
            }
        }
        return titleList
    }

    /**
     *  this method for item click to display dialogue fragment .....
     */
    override fun onItemClicked(position: Int) {

        var isFileNotAvailable = false

//        Toast.makeText(context, position.toString(), Toast.LENGTH_LONG).show()
        val playFragment = PlayAudioDialogueFragment()

        val bundles = Bundle()
        val titles = getListAllFiles()
        for (i in 0 until titles.size) {
            if (list[position].title == titles[i]) {
                bundles.putString("title", list[position].title)
                bundles.putString("path", list[position].path)
                bundles.putString("duration", list[position].duration)
                playFragment.arguments = bundles
                playFragment.show(fragmentManager!!, "Audio")
                isFileNotAvailable = true
            }
        }
        if (!isFileNotAvailable) {
            mAppDataManager.singletonRoom.singleTonRoomData().audioListDao().deleteAudio(list[position].title)
            list.removeAt(position)
            listAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Audio file not exists...", Toast.LENGTH_LONG).show()
        }
    }

    /**
     *  this method for long item click to display dialogue fragment .....
     */
    override fun onItemLongClicked(position: Int) {
        var isFileNotAvailable = false
        val changFragment = ChangeAudioDetailsFragment()
        val bundles = Bundle()

        val titles = getListAllFiles()
        for (i in 0 until titles.size) {
            if (list[position].title == titles[i]) {
                bundles.putString("path", list[position].path)
                bundles.putString("title", list[position].title)
                changFragment.arguments = bundles
                changFragment.show(fragmentManager!!, "Audio")
                isFileNotAvailable = true
            }
        }
        if (!isFileNotAvailable)
        {
            mAppDataManager.singletonRoom.singleTonRoomData().audioListDao().deleteAudio(list[position].title)
            list.removeAt(position)
            listAdapter.notifyDataSetChanged()
            Toast.makeText(context, "Audio file not exists...", Toast.LENGTH_LONG).show()

        }


    }

    override fun onResume() {
        super.onResume()
        listAdapter.notifyDataSetChanged()
//        Toast.makeText(context, "onRessume", Toast.LENGTH_LONG).show()
    }

}
