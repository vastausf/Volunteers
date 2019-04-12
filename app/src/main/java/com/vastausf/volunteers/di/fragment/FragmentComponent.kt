package com.vastausf.volunteers.di.fragment

import com.squareup.picasso.Picasso
import com.vastausf.volunteers.di.application.ApplicationComponent
import com.vastausf.volunteers.presentation.ui.activity.splash.SplashActivity
import com.vastausf.volunteers.presentation.ui.fragment.events.EventsFragment
import com.vastausf.volunteers.presentation.ui.fragment.groups.GroupsFragment
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment
import com.vastausf.volunteers.presentation.ui.fragment.login.LoginFragment
import com.vastausf.volunteers.presentation.ui.fragment.main.MainFragment
import com.vastausf.volunteers.presentation.ui.fragment.newEvent.NewEventFragment
import com.vastausf.volunteers.presentation.ui.fragment.profile.ProfileFragment
import com.vastausf.volunteers.presentation.ui.fragment.registration.RegistrationFragment
import com.vastausf.volunteers.presentation.ui.fragment.settings.SettingsFragment
import dagger.Component

@Component(
    modules = [FragmentModule::class],
    dependencies = [ApplicationComponent::class])
interface FragmentComponent {

    val picasso: Picasso

    fun inject(splashFragment: SplashActivity)

    fun inject(mainFragment: MainFragment)

    fun inject(loginFragment: LoginFragment)

    fun inject(registrationFragment: RegistrationFragment)

    fun inject(groupsFragment: GroupsFragment)

    fun inject(eventsFragment: EventsFragment)

    fun inject(profileFragment: ProfileFragment)

    fun inject(settingsFragment: SettingsFragment)

    fun inject(newEventFragment: NewEventFragment)

    fun inject(loadImageFragment: LoadImageFragment)

}
