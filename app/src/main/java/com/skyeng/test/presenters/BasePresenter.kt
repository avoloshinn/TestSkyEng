package com.skyeng.test.presenters

import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class BasePresenter {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    fun stopSubscriptions() {
        disposables.clear()
    }
}