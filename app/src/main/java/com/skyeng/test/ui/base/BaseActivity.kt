package com.skyeng.test.ui.base

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class BaseActivity: DaggerAppCompatActivity() {

    val TAG = javaClass.simpleName

    abstract fun getLayoutResource(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
    }
}