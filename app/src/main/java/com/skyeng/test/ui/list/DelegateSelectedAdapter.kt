package com.skyeng.test.ui.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 25.01.2019 by Andrey Voloshin.
 */
interface DelegateSelectedAdapter<in T> where T : ViewType {
    fun onCreateViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder?, item: T)
}