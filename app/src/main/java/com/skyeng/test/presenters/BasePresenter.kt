package com.skyeng.test.presenters

import io.reactivex.disposables.CompositeDisposable

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
abstract class BasePresenter<T> {

    protected val disposables: CompositeDisposable = CompositeDisposable()

    abstract fun attachView(view: T)

    abstract fun detachView()

    fun stopSubscriptions() {
        disposables.clear()
    }
}