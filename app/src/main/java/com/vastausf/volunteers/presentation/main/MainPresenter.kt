package com.vastausf.volunteers.presentation.main

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.base.BasePresenter
import com.vastausf.volunteers.presentation.login.LoginActivity
import javax.inject.Inject

@InjectViewState
class MainPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BasePresenter<MainView>() {

    fun onViewCreated() {
        viewState.showToast("View created")
    }

    fun onLogOut() {
        volunteersTokenStore.removeTokens()
        viewState.startActivity(LoginActivity::class.java, finish = true)
    }

}