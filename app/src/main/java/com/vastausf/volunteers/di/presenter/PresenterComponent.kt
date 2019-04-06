package com.vastausf.volunteers.di.presenter

import com.vastausf.volunteers.di.application.ApplicationComponent
import dagger.Component

@Component(
    modules = [PresenterModule::class],
    dependencies = [ApplicationComponent::class])
interface PresenterComponent {

}