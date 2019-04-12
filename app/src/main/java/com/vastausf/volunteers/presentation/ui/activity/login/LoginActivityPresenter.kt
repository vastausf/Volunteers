package com.vastausf.volunteers.presentation.ui.activity.login

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivityPresenter
import javax.inject.Inject

@InjectViewState
class LoginActivityPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore
) : BaseActivityPresenter<LoginActivityView>()
