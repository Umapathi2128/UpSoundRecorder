package com.uma.upsoundrecorder.ui.mainactivity

import android.database.DataSetObserver
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.uma.upsoundrecorder.ui.listrecordings.ListFragment
import com.uma.upsoundrecorder.ui.recordfragment.RecordFragment

/**
 * Created by Umapathi on 28/01/19.
 * Copyright Indyzen Inc, @2019
 */
class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> {
                RecordFragment()
            }
            1 -> {
                ListFragment()
            }
            else -> {
                RecordFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    fun test(){
        notifyDataSetChanged()
    }


    override fun getItemPosition(`value`: Any): Int {
//        if (value is ListFragment){
//            test()
//        }
        return PagerAdapter.POSITION_NONE
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Record"
            1 -> "List"
            else -> ""
        }
    }

}