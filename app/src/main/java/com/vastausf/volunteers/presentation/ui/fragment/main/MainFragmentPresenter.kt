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
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import com.vastausf.volunteers.presentation.ui.fragment.login.LoginFragment
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
        loadEventList()
        loadGroupList()
    }

    fun loadEventList() {
        viewState.eventsLoadState(true)

        volunteersApiClient
            .findEventsByParameters(0, 20, EventDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.eventsLoadState(false)
            }
            .subscribe(::onEventsLoadSuccess, ::onEventsLoadError)
            .unsubscribeOnDestroy()
    }

    fun loadMoreEventList(offset: Int) {
        viewState.eventsLoadState(true)

        volunteersApiClient
            .findEventsByParameters(offset, 20, EventDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.eventsLoadState(false)
            }
            .subscribe(::onEventsLoadMoreSuccess, ::onEventsLoadError)
            .unsubscribeOnDestroy()
    }

    private fun onEventsLoadSuccess(findEventsByParameters: FindEventsByParametersI) {
        viewState.bindEventList(findEventsByParameters.events)
    }

    private fun onEventsLoadMoreSuccess(findEventsByParameters: FindEventsByParametersI) {
        viewState.bindMoreEventList(findEventsByParameters.events)
    }

    private fun onEventsLoadError(error: Throwable) {
        viewState.showToast(error::class.java.name)
    }

    fun loadGroupList() {
        viewState.groupsLoadState(true)

        volunteersApiClient
            .findGroupsByParameters(0, 20, GroupDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.groupsLoadState(false)
            }
            .subscribe(::onGroupsLoadSuccess, ::onGroupsLoadError)
            .unsubscribeOnDestroy()
    }

    fun loadMoreGroupList(offset: Int) {
        viewState.eventsLoadState(true)

        volunteersApiClient
            .findGroupsByParameters(offset, 20, GroupDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                viewState.eventsLoadState(false)
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

    fun likeEvent(eventId: Long, currentState: Boolean) {
        volunteersApiClient.likeEvent(eventId, !currentState)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (compositeDisposable.size() <= 1)
                    viewState.loadingProgress(false)
            }
            .subscribe(::onLikeSuccess, ::onLikeError)
            .unsubscribeOnDestroy()
    }

    private fun onLikeSuccess(eventsLikeI: EventsLikeI) { }

    private fun onLikeError(error: Throwable) {
        viewState.showToast(volunteersApplication.getString(R.string.unknown_error))

        error.printStackTrace()
    }

    fun joinEvent(eventId: Long, currentState: Boolean) {
        volunteersApiClient.joinEvent(eventId, !currentState)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (compositeDisposable.size() <= 1)
                    viewState.loadingProgress(false)
            }
            .subscribe(::onJoinSuccess, ::onJoinError)
            .unsubscribeOnDestroy()
    }

    private fun onJoinSuccess(eventsJoinI: EventsJoinI) { }

    private fun onJoinError(error: Throwable) {
        viewState.showToast(volunteersApplication.getString(R.string.unknown_error))

        error.printStackTrace()
    }

    fun onLogOut() {
        volunteersTokenStore.removeTokens()
        viewState.launchFragment(LoginFragment(), finish = true)
    }

}