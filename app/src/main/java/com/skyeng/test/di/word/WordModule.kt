package com.skyeng.test.di.word

import com.skyeng.test.interactors.SearchInteractor
import com.skyeng.test.interactors.SearchInteractorImpl
import com.skyeng.test.presenters.WordPresenter
import com.skyeng.test.presenters.WordPresenterImpl
import com.skyeng.test.ui.word.WordFragment
import com.skyeng.test.ui.word.WordView
import dagger.Binds
import dagger.Module

/**
 * Created on 12.07.2020 by Andrey Voloshin.
 */
@Module
interface WordModule {

    @Binds
    fun provideWordInteractor(interactor: SearchInteractorImpl): SearchInteractor

    @Binds
    fun provideWordPresenter(presenter: WordPresenterImpl): WordPresenter

    @Binds
    fun provideWordFragment(fragment: WordFragment): WordView
}