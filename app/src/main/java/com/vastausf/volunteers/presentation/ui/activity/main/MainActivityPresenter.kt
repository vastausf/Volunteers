package com.vastausf.volunteers.presentation.ui.activity.main

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivityPresenter
import javax.inject.Inject

@InjectViewState
class MainActivityPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BaseActivityPresenter<MainActivityView>()
