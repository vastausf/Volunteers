package com.vastausf.volunteers.di.application

import android.content.SharedPreferences
import com.vastausf.volunteers.VolunteersApplication
import dagger.Component

@Component(
    modules = [ApplicationModule::class])
interface ApplicationComponent {

    val volunteersApplication: VolunteersApplication

    val volunteersApplicationSharedPreferences: SharedPreferences

}