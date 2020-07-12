package com.skyeng.test.ui.list

import android.os.Parcel
import com.skyeng.test.utils.Constants

/**
 * Created on 23.01.2019 by Andrey Voloshin.
 */
abstract class PaginationAdapter(
        listener: OnItemClickedListener<ViewType>
) : BaseAdapter<ViewType>(listener) {
    var hasBottomProgress: Boolean = true
    var hasBottomReloader: Boolean = false

    override fun reinit(data: MutableList<ViewType>) {
        val isOnePageWithProgress = (items.size - 1 == Constants.ITEMS_PER_PAGE)
        if (isOnePageWithProgress && hasBottomReloader) {
            data.add(getReloadItem())
        } else if (hasBottomProgress) {
            data.add(getProgressItem())
            hasBottomReloader = false
        }

        super.reinit(data)
    }

    fun addData(data: MutableList<ViewType>) {
        if (hasBottomProgress) {
            items.addAll(items.lastIndex, data)
        } else {
            items.removeAt(items.lastIndex)
            items.addAll(data)
        }

        notifyDataSetChanged()
    }

    fun showReloadItem() {
        val lastIndex = items.lastIndex
        if (lastIndex == -1) {
            return
        }
        items[lastIndex] = getReloadItem()
        notifyItemChanged(lastIndex)
        hasBottomReloader = true
    }

    fun hideReloadItem() {
        val lastIndex = items.lastIndex
        items[lastIndex] = getProgressItem()
        notifyItemChanged(lastIndex)
        hasBottomReloader = false
    }

    private fun getProgressItem(): ViewType = object : ViewType {
        override fun writeToParcel(dest: Parcel?, flags: Int) {
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun getViewType(): Int = ListItemType.PROGRESS.ordinal
    }

    private fun getReloadItem(): ViewType = object : ViewType {
        override fun writeToParcel(dest: Parcel?, flags: Int) {
        }

        override fun describeContents(): Int {
            return 0
        }

        override fun getViewType(): Int = ListItemType.RELOAD.ordinal
    }
}