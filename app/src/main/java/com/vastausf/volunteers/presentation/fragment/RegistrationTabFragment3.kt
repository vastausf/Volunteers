package com.vastausf.volunteers.presentation.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_registration_tab_3.view.*
import java.util.Calendar

class RegistrationTabFragment3 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_registration_tab_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.cvUserBirthday.maxDate = Calendar.getInstance().apply {
            add(Calendar.YEAR, -14)
        }.timeInMillis
    }

}