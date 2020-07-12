package com.skyeng.test.ui.base

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
interface BaseView {
    fun showProgress()
    fun hideProgress()
    fun showError(error: String)
    fun showError(error: Int)
}