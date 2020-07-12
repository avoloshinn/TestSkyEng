package com.skyeng.test.ui.list

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created on 23.01.2019 by Andrey Voloshin.
 */
class InfiniteScrollListener(
        private val visibleThreshold: Int,
        private val layoutManager: LinearLayoutManager,
        private val getMoreItems: () -> Unit) : RecyclerView.OnScrollListener() {

    private var previousTotal = 0
    private var wasLoaded = false

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy != 0) {
            val totalItemCount = layoutManager.itemCount
            val visibleItemCount = recyclerView.childCount
            val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

            if (wasLoaded && totalItemCount != previousTotal) {
                wasLoaded = false
            }

            if (!wasLoaded && (totalItemCount - (firstVisibleItem + visibleItemCount)) <= visibleThreshold) {
                previousTotal = totalItemCount
                wasLoaded = true
                getMoreItems()
            }
        }
    }
}