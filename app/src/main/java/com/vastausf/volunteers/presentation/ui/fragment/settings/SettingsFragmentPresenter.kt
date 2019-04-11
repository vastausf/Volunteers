package com.vastausf.volunteers.presentation.ui.fragment.settings

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.activity.login.LoginActivity
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import javax.inject.Inject

@InjectViewState
class SettingsFragmentPresenter
@Inject
constructor(
    private val volunteersTokenStore: VolunteersTokenStore
) : BaseFragmentPresenter<SettingsFragmentView>() {

    fun onLogOut() {
        volunteersTokenStore.removeTokens()
        viewState.launchActivity(LoginActivity(), finish = true)
    }

}