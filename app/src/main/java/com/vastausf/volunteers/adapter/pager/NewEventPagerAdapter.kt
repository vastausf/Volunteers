package com.vastausf.volunteers.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.pages.FragmentDate
import com.vastausf.volunteers.adapter.pager.pages.FragmentDescription
import com.vastausf.volunteers.adapter.pager.pages.FragmentDuration
import com.vastausf.volunteers.adapter.pager.pages.FragmentPlace
import com.vastausf.volunteers.adapter.pager.pages.FragmentTime
import com.vastausf.volunteers.adapter.pager.pages.FragmentTitle
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment

class NewEventPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val fragmentTitle = FragmentTitle()
    val fragmentPlace = FragmentPlace()
    val fragmentDate = FragmentDate()
    val fragmentTime = FragmentTime()
    val fragmentDuration = FragmentDuration()
    val fragmentDescription = FragmentDescription()
    val loadImageFragment = LoadImageFragment()

    val pages = listOf(
        fragmentTitle,
        fragmentPlace,
        fragmentDate,
        fragmentTime,
        fragmentDuration,
        fragmentDescription,
        loadImageFragment
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}
