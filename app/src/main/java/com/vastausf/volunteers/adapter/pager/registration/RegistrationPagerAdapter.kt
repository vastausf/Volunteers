package com.vastausf.volunteers.adapter.pager.registration

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationFragmentLogin
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationFragmentNames
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationFragmentBirthday
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationFragmentPrivacyPolicy
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment

class RegistrationPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val registrationFragmentLogin = RegistrationFragmentLogin()
    val registrationFragmentNames = RegistrationFragmentNames()
    val registrationFragmentBirthday = RegistrationFragmentBirthday()
    val loadImageFragment = LoadImageFragment()
    val registrationFragmentPrivacyPolicy = RegistrationFragmentPrivacyPolicy()
    
    val pages = listOf(
        registrationFragmentLogin,
        registrationFragmentNames,
        registrationFragmentBirthday,
        loadImageFragment,
        registrationFragmentPrivacyPolicy
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}
