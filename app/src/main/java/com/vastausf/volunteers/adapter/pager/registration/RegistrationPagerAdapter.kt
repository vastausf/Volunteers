package com.vastausf.volunteers.adapter.pager.registration

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment0
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment1
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment2
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment3

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