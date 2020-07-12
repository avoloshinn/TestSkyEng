package com.skyeng.test.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 18.01.2019 by Andrey Voloshin.
 */
interface DelegateAdapter<in T> where T : ViewType {
    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: T)
    fun update(item: T)
}