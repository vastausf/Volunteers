package com.vastausf.volunteers.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.pages.FragmentAccept
import com.vastausf.volunteers.adapter.pager.pages.FragmentLogin
import com.vastausf.volunteers.adapter.pager.pages.FragmentNames
import com.vastausf.volunteers.adapter.pager.pages.FragmentBirthday
import com.vastausf.volunteers.adapter.pager.pages.FragmentPrivacyPolicy
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment

class RegistrationPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val registrationFragmentLogin = FragmentLogin()
    val registrationFragmentNames = FragmentNames()
    val registrationFragmentBirthday = FragmentBirthday()
    val loadImageFragment = LoadImageFragment()
    val fragmentPrivacyPolicy = FragmentPrivacyPolicy()
    val fragmentAccept = FragmentAccept()

    
    val pages = listOf(
        registrationFragmentLogin,
        registrationFragmentNames,
        registrationFragmentBirthday,
        loadImageFragment,
        fragmentPrivacyPolicy,
        fragmentAccept
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}
