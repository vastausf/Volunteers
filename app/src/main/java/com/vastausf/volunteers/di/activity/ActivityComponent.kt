package com.vastausf.volunteers.di.activity

import com.vastausf.volunteers.di.application.ApplicationComponent
import com.vastausf.volunteers.presentation.ui.activity.main.MainActivity
import com.vastausf.volunteers.presentation.ui.activity.splash.SplashActivity
import dagger.Component

@Component(dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(mainActivity: MainActivity)

}
