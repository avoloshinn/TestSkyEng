package com.skyeng.test.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class BaseFragment : DaggerFragment(), BaseView {

    val TAG = javaClass.simpleName

    abstract fun getFragmentLayout(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getFragmentLayout(), container, false)
    }
}