package com.uma.upsoundrecorder.ui.listrecordings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.uma.upsoundrecorder.R
import com.uma.upsoundrecorder.databinding.AudioListBinding

/**
 * Created by Umapathi on 01/02/19.
 * Copyright Indyzen Inc, @2019
 */
class ListAdapter(var list: ArrayList<ListFragmentModel>, var listener: ListOnClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var audioListBinding: AudioListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        audioListBinding =
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.audio_list, parent, false)
        return MyViewHolder(audioListBinding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * This method for remove item on particular position....
     */
    fun remove(position: Int) {
//        notifyItemChanged(position)
        notifyItemRemoved(position)
        list.removeAt(position)
//        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).onBind(list[position])


    }


    inner class MyViewHolder(val view: AudioListBinding) : RecyclerView.ViewHolder(view.root) {

        fun onBind(item: ListFragmentModel) {
            view.listAdapterData = item
//            listener.onItemClicked(item,listener)

            itemView.setOnClickListener {
                listener.onItemClicked(adapterPosition)
            }

            itemView.setOnLongClickListener {
                listener.onItemLongClicked(adapterPosition)
                true}
        }
    }
}