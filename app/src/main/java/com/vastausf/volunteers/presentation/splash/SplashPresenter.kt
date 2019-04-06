package com.vastausf.volunteers.presentation.splash

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.base.BasePresenter
import com.vastausf.volunteers.presentation.login.LoginActivity
import com.vastausf.volunteers.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class SplashPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApplicationSharedPreferences: SharedPreferences
) : BasePresenter<SplashView>() {

    fun onViewShowed() {
        if (volunteersApplicationSharedPreferences.contains("volServerAccessToken")) {
            viewState.startActivity(MainActivity::class.java)
        } else {
            viewState.startActivity(LoginActivity::class.java)
        }
    }

}