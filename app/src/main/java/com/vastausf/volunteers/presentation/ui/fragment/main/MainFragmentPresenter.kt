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
        viewState.loadingProgress(true)

        loadEventList()
    }

    private fun loadEventList() {
        volunteersApiClient
            .findEventsByParameters(0, 20, EventDataSearch())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {
                if (compositeDisposable.size() <= 1)
                    viewState.loadingProgress(false)
            }
            .subscribe(::onEventsLoadSuccess, ::onEventsLoadError)
            .unsubscribeOnDestroy()
    }

    private fun onEventsLoadSuccess(findEventsByParameters: FindEventsByParametersI) {
        viewState.bindEventList(findEventsByParameters.events)
    }

    private fun onEventsLoadError(error: Throwable) {
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