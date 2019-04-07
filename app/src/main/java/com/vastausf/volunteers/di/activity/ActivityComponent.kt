package com.vastausf.volunteers.di.activity

import com.squareup.picasso.Picasso
import com.vastausf.volunteers.di.application.ApplicationComponent
import com.vastausf.volunteers.presentation.login.LoginActivity
import com.vastausf.volunteers.presentation.main.MainActivity
import com.vastausf.volunteers.presentation.registration.RegistrationActivity
import com.vastausf.volunteers.presentation.splash.SplashActivity
import dagger.Component

@Component(
    modules = [ActivityModule::class],
    dependencies = [ApplicationComponent::class])
interface ActivityComponent {

    val picasso: Picasso

    fun inject(splashActivity: SplashActivity)

    fun inject(mainActivity: MainActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(registrationActivity: RegistrationActivity)

}