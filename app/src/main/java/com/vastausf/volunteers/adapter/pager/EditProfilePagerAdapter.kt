package com.vastausf.volunteers.adapter.pager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.vastausf.volunteers.adapter.pager.pages.FragmentAbout
import com.vastausf.volunteers.adapter.pager.pages.FragmentAccept
import com.vastausf.volunteers.adapter.pager.pages.FragmentContacts
import com.vastausf.volunteers.adapter.pager.pages.FragmentNames
import com.vastausf.volunteers.model.volunteers.data.UserDataEdit
import com.vastausf.volunteers.presentation.ui.fragment.loadImage.LoadImageFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_load_image.*
import kotlinx.android.synthetic.main.fragment_names.*

class EditProfilePagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {
    val fragmentNames = FragmentNames()
    val loadImageFragment = LoadImageFragment()
    val aboutFragment = FragmentAbout()
    val contactsFragment = FragmentContacts()
    val acceptFragment = FragmentAccept()

    val pages = listOf(
        fragmentNames,
        aboutFragment,
        loadImageFragment,
        contactsFragment,
        acceptFragment
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    fun bindProfile(userDataEdit: UserDataEdit) {
        userDataEdit.firstName?.let { fragmentNames.etFirstName.setText(it) }
        userDataEdit.lastName?.let { fragmentNames.etLastName.setText(it) }
        userDataEdit.middleName?.let { fragmentNames.etMiddleName.setText(it) }

        userDataEdit.about?.let { aboutFragment.etAbout.setText(it) }

        userDataEdit.image?.let { loadImageFragment.etImageLink.setText(it) }
        
        userDataEdit.phoneNumber?.let { contactsFragment.etPhone.setText(it) }
        userDataEdit.email?.let { contactsFragment.etEmail.setText(it) }
        userDataEdit.link?.let { contactsFragment.etLink.setText(it) }
    }

}
