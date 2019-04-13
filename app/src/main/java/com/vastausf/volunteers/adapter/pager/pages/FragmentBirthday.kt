package com.vastausf.volunteers.adapter.pager.pages

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_birthday.view.*
import java.util.Calendar

class FragmentBirthday : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_birthday, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.dpBirthday.maxDate = Calendar.getInstance()
            .apply {
                add(Calendar.YEAR, -view.resources.getInteger(R.integer.min_years_old))
            }
            .timeInMillis
    }

}
