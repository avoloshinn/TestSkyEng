package com.skyeng.test.ui.list

import android.view.View

/**
 * Created on 18.01.2019 by Andrey Voloshin.
 */
interface OnItemClickedListener<in T> where T: ViewType {
    fun onItemClicked(item: T, view: View?)
}