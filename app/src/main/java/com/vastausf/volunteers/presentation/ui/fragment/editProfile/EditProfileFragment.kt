package com.vastausf.volunteers.presentation.ui.fragment.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.pager.EditProfilePagerAdapter
import com.vastausf.volunteers.adapter.pager.pages.FragmentAccept
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.model.volunteers.data.UserDataEdit
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_about.*
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.fragment_edit_profile.view.*
import kotlinx.android.synthetic.main.fragment_load_image.*
import kotlinx.android.synthetic.main.fragment_names.*
import javax.inject.Inject

class EditProfileFragment : BaseFragment(), EditProfileFragmentView, FragmentAccept.AcceptListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: EditProfileFragmentPresenter

    private lateinit var editProfilePagerAdapter: EditProfilePagerAdapter

    override fun onAccept() {
        val firstName = editProfilePagerAdapter.fragmentNames.etFirstName.text.toString()
            .ifBlank { null }
        val lastName = editProfilePagerAdapter.fragmentNames.etLastName.text.toString()
            .ifBlank { null }
        val middleName = editProfilePagerAdapter.fragmentNames.etMiddleName.text.toString()
            .ifBlank { null }

        val about = editProfilePagerAdapter.aboutFragment.etAbout.text.toString()
            .ifBlank { null }

        val image = editProfilePagerAdapter.loadImageFragment.etImageLink.text.toString()
            .ifEmpty { null }

        val phone = editProfilePagerAdapter.contactsFragment.etPhone.text.toString()
            .ifEmpty { null }
        val email = editProfilePagerAdapter.contactsFragment.etEmail.text.toString()
            .ifEmpty { null }
        val link = editProfilePagerAdapter.contactsFragment.etLink.text.toString()
            .ifBlank { null }

        presenter.editProfile(
            UserDataEdit(firstName, lastName, middleName, about, phone, image, email, link)
        )
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_edit_profile, container, false)

        editProfilePagerAdapter = EditProfilePagerAdapter(childFragmentManager)

        view.vpEditProfile.apply {
            adapter = editProfilePagerAdapter
            offscreenPageLimit = editProfilePagerAdapter.count
        }

        presenter.loadProfile()

        return view
    }

    override fun bindProfile(userDataEdit: UserDataEdit) {
        editProfilePagerAdapter.bindProfile(userDataEdit)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            DaggerFragmentComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)
        }
        super.onCreate(savedInstanceState)
    }

}