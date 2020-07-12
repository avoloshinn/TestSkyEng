package com.skyeng.test.di

import com.skyeng.test.di.search.SearchModule
import com.skyeng.test.di.word.WordModule
import com.skyeng.test.ui.search.SearchFragment
import com.skyeng.test.ui.word.WordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
@Module
interface MainFragmentInjector {

    @ContributesAndroidInjector(modules = [SearchModule::class])
    fun provideSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = [WordModule::class])
    fun provideWordFragment(): WordFragment
}