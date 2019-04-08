package com.vastausf.volunteers.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vastausf.volunteers.R
import kotlinx.android.synthetic.main.fragment_registration_tab_4.*

class RegistrationTabFragment3 : Fragment() {

    private lateinit var registrationListener: RegistrationListener

    interface RegistrationListener {
        fun onSignUpClick()
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_registration_tab_4, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wvPrivacyPolicy.apply {
            loadUrl(getString(R.string.volunteers_privacy_policy_url))
        }

        bSignUp.setOnClickListener {
            registrationListener.onSignUpClick()
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        registrationListener = context as RegistrationListener
    }

}