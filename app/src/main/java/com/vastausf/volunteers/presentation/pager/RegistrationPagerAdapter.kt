package com.vastausf.volunteers.presentation.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment0
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment1
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment2
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment3

class RegistrationPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val pages = listOf(
        RegistrationTabFragment0(),
        RegistrationTabFragment1(),
        RegistrationTabFragment2(),
        RegistrationTabFragment3()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}