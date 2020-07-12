package com.skyeng.test.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyeng.test.R

/**
 * Created on 27.01.2019 by Andrey Voloshin.
 */
class ProgressDelegateAdapter : DelegateAdapter<ViewType> {

    class ProgressViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {

        fun bind() {
            // do nothing
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder =
            ProgressViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_progress, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: ViewType) = (holder as ProgressViewHolder).bind()

    override fun update(item: ViewType) {}
}