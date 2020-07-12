package com.skyeng.test.ui.list

import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 18.01.2019 by Andrey Voloshin.
 */
abstract class BaseAdapter<T>(protected val listener: OnItemClickedListener<T>? = null) : RecyclerView.Adapter<RecyclerView.ViewHolder>() where T : ViewType {

    protected var delegateAdapters: MutableMap<Int, DelegateAdapter<T>> = ArrayMap()
    protected var items: MutableList<T> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            delegateAdapters[viewType]!!.onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            delegateAdapters[getItemViewType(position)]!!.onBindViewHolder(holder, items[position])

    override fun getItemViewType(position: Int): Int = items[position].getViewType()

    override fun getItemCount(): Int = items.size

    open fun reinit(data: MutableList<T>) {
        items = data
        notifyDataSetChanged()
    }

    open fun update(item: T) {
        for (adapter in delegateAdapters.values) {
            adapter.update(item)
        }

        notifyDataSetChanged()
    }

    fun isEmpty() = itemCount == 0

    fun isNotEmpty() = itemCount > 0
}