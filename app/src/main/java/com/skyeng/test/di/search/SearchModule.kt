package com.skyeng.test.di.search

import com.skyeng.test.interactors.SearchInteractor
import com.skyeng.test.interactors.SearchInteractorImpl
import com.skyeng.test.presenters.SearchPresenter
import com.skyeng.test.presenters.SearchPresenterImpl
import com.skyeng.test.ui.search.SearchFragment
import com.skyeng.test.ui.search.SearchView
import dagger.Binds
import dagger.Module

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
@Module
interface SearchModule {

    @Binds
    fun provideSearchInteractor(interactor: SearchInteractorImpl): SearchInteractor

    @Binds
    fun provideSearchPresenter(presenter: SearchPresenterImpl): SearchPresenter

    @Binds
    fun provideSearchFragment(fragment: SearchFragment): SearchView
}