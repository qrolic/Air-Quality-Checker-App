package com.qrolic.blueair.di

import com.qrolic.blueair.repository.HomeRepository
import com.qrolic.blueair.repository.SearchRepository
import com.qrolic.blueair.utils.ApiInterface
import com.qrolic.blueair.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /* api interface */

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(200, TimeUnit.SECONDS)
            .readTimeout(200, TimeUnit.SECONDS).build()


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)


    /* repositories */
    @Provides
    fun providesSearchRepository(apiInterface: ApiInterface): SearchRepository =
        SearchRepository(apiInterface)

    @Provides
    fun providesHomeRepository(apiInterface: ApiInterface): HomeRepository =
        HomeRepository(apiInterface)



}