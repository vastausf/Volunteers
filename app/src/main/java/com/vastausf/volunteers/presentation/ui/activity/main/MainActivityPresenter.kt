package com.vastausf.volunteers.presentation.ui.activity.main

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivityPresenter
import com.vastausf.volunteers.presentation.ui.fragment.login.LoginFragment
import com.vastausf.volunteers.presentation.ui.fragment.main.MainFragment
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BaseActivityPresenter<MainActivityView>() {

    fun onCreate() {
        if (volunteersTokenStore.accessToken.isEmpty()) {
            viewState.launchFragment(LoginFragment(), true)
        } else {
            viewState.launchFragment(MainFragment(), true)
        }
    }

}
