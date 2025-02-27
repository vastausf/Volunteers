package com.vastausf.volunteers.presentation.ui.activity.splash

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivityPresenter
import com.vastausf.volunteers.presentation.ui.activity.main.MainActivity
import javax.inject.Inject

@InjectViewState
class SplashActivityPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BaseActivityPresenter<SplashActivityView>() {

    fun onCreate() {
        viewState.launchActivity(MainActivity::class.java, true)
    }

}
