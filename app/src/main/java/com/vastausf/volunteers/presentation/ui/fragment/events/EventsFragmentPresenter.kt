package com.vastausf.volunteers.presentation.ui.fragment.events

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.data.EventDataSearch
import com.vastausf.volunteers.model.volunteers.data.EventsJoinI
import com.vastausf.volunteers.model.volunteers.data.EventsLikeI
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersI
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import com.vastausf.volunteers.presentation.ui.fragment.newEvent.NewEventFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class EventsFragmentPresenter
@Inject
constructor(
    private val volunteersApiClient: VolunteersApiClient,
    private val volunteersApplication: VolunteersApplication
) : BaseFragmentPresenter<EventsFragmentView>() {

    fun onViewCreated() {
        loadEventList()
    }

    fun loadEventList() {
        viewState.eventsLoadState(true)

        volunteersApiClient
            .findEventsByParameters(0,
                volunteersApplication.resources.getInteger(R.integer.load_items), EventDataSearch())
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
            .findEventsByParameters(offset,
                volunteersApplication.resources.getInteger(R.integer.load_items), EventDataSearch())
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

    private fun onLikeSuccess(eventsLikeI: EventsLikeI) {}

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

    private fun onJoinSuccess(eventsJoinI: EventsJoinI) {}

    private fun onJoinError(error: Throwable) {
        viewState.showToast(volunteersApplication.getString(R.string.unknown_error))

        error.printStackTrace()
    }

    fun onNewEventClick() {
        viewState.launchFragment(NewEventFragment())
    }

}
