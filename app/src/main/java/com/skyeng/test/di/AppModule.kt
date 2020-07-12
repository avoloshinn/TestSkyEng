package com.skyeng.test.di

import com.skyeng.test.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideContext(app: App) = app.applicationContext
}