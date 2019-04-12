package com.vastausf.volunteers.presentation.ui.fragment.groups

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersI
import com.vastausf.volunteers.model.volunteers.data.GroupDataSearch
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class GroupsFragmentPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApiClient: VolunteersApiClient
) : BaseFragmentPresenter<GroupsFragmentView>() {

    fun onViewCreated() {
        loadGroupList()
    }

    fun loadGroupList() {
        viewState.groupsLoadState(true)

        volunteersApiClient
            .findGroupsByParameters(0,
                volunteersApplication.resources.getInteger(R.integer.load_items), GroupDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.groupsLoadState(false)
            }
            .subscribe(::onGroupsLoadSuccess, ::onGroupsLoadError)
            .unsubscribeOnDestroy()
    }

    fun loadMoreGroupList(offset: Int) {
        viewState.groupsLoadState(true)

        volunteersApiClient
            .findGroupsByParameters(offset,
                volunteersApplication.resources.getInteger(R.integer.load_items), GroupDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.groupsLoadState(false)
            }
            .subscribe(::onGroupsLoadMoreSuccess, ::onGroupsLoadError)
            .unsubscribeOnDestroy()
    }

    private fun onGroupsLoadSuccess(findGroupsByParametersI: FindGroupsByParametersI) {
        viewState.bindGroupList(findGroupsByParametersI.groups)
    }

    private fun onGroupsLoadMoreSuccess(findGroupsByParametersI: FindGroupsByParametersI) {
        viewState.bindMoreGroupList(findGroupsByParametersI.groups)
    }

    private fun onGroupsLoadError(error: Throwable) {
        viewState.showToast(error::class.java.name)
    }

}
