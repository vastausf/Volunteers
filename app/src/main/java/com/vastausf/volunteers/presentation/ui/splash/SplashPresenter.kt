package com.vastausf.volunteers.presentation.ui.splash

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.base.BasePresenter
import com.vastausf.volunteers.presentation.ui.login.LoginActivity
import com.vastausf.volunteers.presentation.ui.main.MainActivity
import javax.inject.Inject

@InjectViewState
class SplashPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BasePresenter<SplashView>() {

    fun onViewShowed() {
        if (volunteersTokenStore.accessToken != null) {
            viewState.startActivity(MainActivity::class.java, finish = true)
        } else {
            viewState.startActivity(LoginActivity::class.java, finish = true)
        }
    }

}