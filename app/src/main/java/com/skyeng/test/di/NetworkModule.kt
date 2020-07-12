package com.skyeng.test.di

import android.content.Context
import android.net.ConnectivityManager
import com.bumptech.glide.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skyeng.test.di.scopes.ActivityScope
import com.skyeng.test.network.SkyengApi
import com.skyeng.test.utils.Constants
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created on 11.07.2020 by Andrey Voloshin.
 */
@Module
class NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE)
    }

    @ActivityScope
    @Provides
    fun provideHttpClient(loggingInterceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {
        val TIMEOUT_SEC: Long = 10

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .connectTimeout(TIMEOUT_SEC, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    fun provideSkyengApi(retrofit: Retrofit): SkyengApi = retrofit.create(SkyengApi::class.java)

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .client(client)
            .build()
    }
}