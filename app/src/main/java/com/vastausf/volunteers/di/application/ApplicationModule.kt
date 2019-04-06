package com.vastausf.volunteers.di.application

import android.content.Context
import android.content.SharedPreferences
import com.vastausf.volunteers.VolunteersApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(
    private val volunteersApplication: VolunteersApplication
) {

    @Provides
    fun provideVolunteersApplication(): VolunteersApplication =
        volunteersApplication.instance

    @Provides
    fun provideVolunteersApplicationSharedPreferences(volunteersApplication: VolunteersApplication): SharedPreferences =
        volunteersApplication.getSharedPreferences(volunteersApplication.packageName, Context.MODE_PRIVATE)

}