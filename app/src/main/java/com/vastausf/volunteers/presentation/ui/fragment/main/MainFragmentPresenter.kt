package com.vastausf.volunteers.presentation.ui.fragment.main

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import javax.inject.Inject

@InjectViewState
class MainFragmentPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore,
    private val volunteersApiClient: VolunteersApiClient
) : BaseFragmentPresenter<MainFragmentView>() {

    fun onViewCreated() {

    }

}
