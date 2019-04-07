package com.vastausf.volunteers

import android.app.Application
import com.vastausf.volunteers.di.application.ApplicationComponent
import com.vastausf.volunteers.di.application.ApplicationModule
import com.vastausf.volunteers.di.application.DaggerApplicationComponent

class VolunteersApplication : Application() {
    lateinit var instance: VolunteersApplication

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}