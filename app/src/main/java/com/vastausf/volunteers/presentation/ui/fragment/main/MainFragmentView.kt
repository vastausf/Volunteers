package com.vastausf.volunteers.presentation.ui.fragment.main

import com.vastausf.volunteers.model.volunteers.data.EventDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentView

interface MainFragmentView : BaseFragmentView {

    fun bindEventList(events: List<EventDataFull>)

    fun bindMoreEventList(events: List<EventDataFull>)

}