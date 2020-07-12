package com.skyeng.test.ui.list

import android.os.Parcelable

/**
 * Created on 18.01.2019 by Andrey Voloshin.
 */
interface ViewType : Parcelable{
    fun getViewType(): Int
}