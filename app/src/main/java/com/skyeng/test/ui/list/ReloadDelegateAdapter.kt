package com.skyeng.test.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.test.R

/**
 * Created on 27.01.2019 by Andrey Voloshin.
 */
class ReloadDelegateAdapter(
        private val listener: OnItemClickedListener<ViewType>) : DelegateAdapter<ViewType> {

    class ReloadViewHolder(rootView: View,
                           private val listener: OnItemClickedListener<ViewType>) : RecyclerView.ViewHolder(rootView) {

        fun bind(item: ViewType) {
            val button = itemView.findViewById<View>(R.id.button)
            button.setOnClickListener { listener.onItemClicked(item, button) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
            ReloadViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_reload, parent, false), listener)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) =
            (holder as ReloadViewHolder).bind(item)

    override fun update(item: ViewType) {}
}