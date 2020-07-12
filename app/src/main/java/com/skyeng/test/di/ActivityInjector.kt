package com.skyeng.test.di

import com.skyeng.test.di.scopes.ActivityScope
import com.skyeng.test.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
@Module
interface ActivityInjector {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainFragmentInjector::class, NetworkModule::class])
    fun provideMainActivity(): MainActivity
}