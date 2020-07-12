package com.skyeng.test.ui

import android.os.Bundle
import com.skyeng.test.R
import com.skyeng.test.navigation.Navigation
import com.skyeng.test.ui.base.BaseActivity
import com.skyeng.test.ui.search.SearchFragment

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
class MainActivity: BaseActivity() {

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigation.showFragmentRoot(supportFragmentManager, SearchFragment.newInstance())
    }
}