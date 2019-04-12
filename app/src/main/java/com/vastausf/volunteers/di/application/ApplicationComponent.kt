package com.vastausf.volunteers.di.application

import android.content.SharedPreferences
import com.squareup.moshi.Moshi
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.model.volunteers.VolunteersApiAuthenticator
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersApiService
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import dagger.Component
import okhttp3.OkHttpClient

@Component(
    modules = [ApplicationModule::class])
interface ApplicationComponent {

    val volunteersApplication: VolunteersApplication

    val volunteersApplicationSharedPreferences: SharedPreferences

    val volunteersTokenStore: VolunteersTokenStore

    val applicationDataStore: ApplicationDataStore

    val okHttpClient: OkHttpClient

    val volunteersApiAuthenticator: VolunteersApiAuthenticator

    val moshi: Moshi

    val volunteersApiService: VolunteersApiService

    val volunteersApiClient: VolunteersApiClient

}
