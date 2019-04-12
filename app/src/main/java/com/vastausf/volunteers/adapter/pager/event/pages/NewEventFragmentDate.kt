package com.vastausf.volunteers.adapter.pager.event.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_new_event_date.view.*
import kotlinx.android.synthetic.main.fragment_registration_birthday.view.*
import java.util.Calendar

class NewEventFragmentDate : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_new_event_date, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.dpNewEventDate.minDate = Calendar.getInstance().timeInMillis
    }

}
