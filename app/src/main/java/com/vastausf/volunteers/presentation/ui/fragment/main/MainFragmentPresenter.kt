package com.vastausf.volunteers.presentation.ui.fragment.main

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.model.volunteers.data.EventDataSearch
import com.vastausf.volunteers.model.volunteers.data.EventsJoinI
import com.vastausf.volunteers.model.volunteers.data.EventsLikeI
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersI
import com.vastausf.volunteers.model.volunteers.data.GroupDataSearch
import com.vastausf.volunteers.presentation.ui.activity.login.LoginActivity
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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