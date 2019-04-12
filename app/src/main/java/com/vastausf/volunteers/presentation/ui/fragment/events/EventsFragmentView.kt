package com.vastausf.volunteers.presentation.ui.fragment.events

import com.vastausf.volunteers.model.volunteers.data.EventDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface EventsFragmentView : BaseFragmentView {

    fun bindEventList(events: List<EventDataFull>)

    fun bindMoreEventList(events: List<EventDataFull>)

    fun eventsLoadState(state: Boolean)

}
