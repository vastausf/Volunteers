package com.vastausf.volunteers.adapter.pager.event.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_registration_birthday.view.*
import java.util.Calendar

class NewEventFragmentPlace : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_new_event_place, container, false)
    }

}
