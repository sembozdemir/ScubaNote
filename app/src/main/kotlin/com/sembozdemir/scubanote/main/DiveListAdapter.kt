package com.sembozdemir.scubanote.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sembozdemir.scubanote.R
import com.sembozdemir.scubanote.data.model.Dive
import com.sembozdemir.scubanote.extensions.asString
import com.sembozdemir.scubanote.extensions.plus1
import kotlinx.android.synthetic.main.list_item_dives.view.*
import org.jetbrains.anko.onClick

class DiveListAdapter(var diveList: List<Dive>, val itemClick: (Int, String) -> Unit) :
        RecyclerView.Adapter<DiveListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_dives, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDive(diveList[position])
    }

    override fun getItemCount(): Int = diveList.size

    class ViewHolder(view: View, val itemClick: (Int, String) -> Unit): RecyclerView.ViewHolder(view) {

        fun bindDive(dive: Dive) {
            with(itemView) {
                textViewName.text = dive.name
                textViewNo.text = adapterPosition.plus1()
                textViewDate.text = dive.date.asString()
                onClick { itemClick(adapterPosition, dive.name) }
            }
        }
    }

    fun refresh(diveList: List<Dive>) {
        this.diveList = diveList
        notifyDataSetChanged()
    }
}