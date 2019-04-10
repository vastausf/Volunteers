package com.vastausf.volunteers.di.application

import android.content.Context
import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.model.volunteers.VolunteersApiAuthenticator
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersApiService
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class ApplicationModule(
    private val volunteersApplication: VolunteersApplication
) {

    @Provides
    fun provideVolunteersApplication(): VolunteersApplication =
        volunteersApplication.instance

    @Provides
    fun provideVolunteersApplicationSharedPreferences(volunteersApplication: VolunteersApplication): SharedPreferences =
        volunteersApplication.getSharedPreferences(volunteersApplication.packageName,
            Context.MODE_PRIVATE)

    @Provides
    fun provideVolunteersTokenStore(volunteersApiSharedPreferences: SharedPreferences, applicationDataStore: ApplicationDataStore): VolunteersTokenStore =
        VolunteersTokenStore(volunteersApiSharedPreferences, applicationDataStore)

    @Provides
    fun provideApplicationDataStore(volunteersApiSharedPreferences: SharedPreferences): ApplicationDataStore =
        ApplicationDataStore(volunteersApiSharedPreferences)

    @Provides
    fun provideOkHttpClient(volunteersApiAuthenticator: VolunteersApiAuthenticator): OkHttpClient =
        OkHttpClient
            .Builder()
            .authenticator(volunteersApiAuthenticator)
            .build()

    @Provides
    fun provideVolunteersApiAuthenticator(
        volunteersApplication: VolunteersApplication,
        volunteersTokenStore: VolunteersTokenStore,
        moshi: Moshi
    ): VolunteersApiAuthenticator =
        VolunteersApiAuthenticator(volunteersApplication,
            volunteersTokenStore,
            OkHttpClient(),
            moshi)

    @Provides
    fun provideMoshi(): Moshi =
        Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

    @Provides
    fun provideVolunteersApiService(volunteersApplication: VolunteersApplication, okHttpClient: OkHttpClient, moshi: Moshi): VolunteersApiService =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(volunteersApplication.getString(R.string.volunteers_server_base_url))
            .build()
            .create(VolunteersApiService::class.java)

    @Provides
    fun provideVolunteersApiClient(volunteersApiService: VolunteersApiService, volunteersTokenStore: VolunteersTokenStore): VolunteersApiClient =
        VolunteersApiClient(volunteersApiService, volunteersTokenStore)

}