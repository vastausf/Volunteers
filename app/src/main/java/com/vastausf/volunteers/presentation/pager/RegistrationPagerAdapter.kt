package com.vastausf.volunteers.presentation.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment1
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment2
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment3
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment4

class RegistrationPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    private val pages = listOf(
        RegistrationTabFragment1(),
        RegistrationTabFragment2(),
        RegistrationTabFragment3(),
        RegistrationTabFragment4()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}