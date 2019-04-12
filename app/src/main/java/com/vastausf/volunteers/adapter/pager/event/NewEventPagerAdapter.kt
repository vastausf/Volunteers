package com.vastausf.volunteers.adapter.pager.event

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentDate
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentDescription
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentDuration
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentPlace
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentTime
import com.vastausf.volunteers.adapter.pager.event.pages.NewEventFragmentTitle
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment

class NewEventPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val pages = listOf(
        NewEventFragmentTitle(),
        NewEventFragmentPlace(),
        NewEventFragmentDate(),
        NewEventFragmentTime(),
        NewEventFragmentDuration(),
        NewEventFragmentDescription(),
        LoadImageFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

}
