package com.vastausf.volunteers.presentation.main

import android.content.SharedPreferences
import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApplicationSharedPreferences: SharedPreferences
) : BasePresenter<MainView>() {

    fun onViewCreated() {
        viewState.showToast("View created")
    }

}