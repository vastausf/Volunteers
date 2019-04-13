package com.vastausf.volunteers.presentation.ui.fragment.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.model.volunteers.data.UserDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.presentation.ui.fragment.editProfile.EditProfileFragment
import com.vastausf.volunteers.presentation.ui.fragment.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_profile.view.*
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: ProfileFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    private val settingsFragment = SettingsFragment()
    private val editProfileFragment = EditProfileFragment()

    @SuppressLint("SetTextI18n")
    override fun bindUserData(userData: UserDataFull) {
        view?.apply {
            tvProfileName.text = "${userData.firstName} ${userData.lastName}"
            picasso
                .load(userData.image)
                .into(ivProfileToolbar)

            userData.birthday.also { birthday ->
                val calendar = Calendar.getInstance()
                val now = calendar.get(Calendar.YEAR)
                calendar.time = Date(birthday)
                tvProfileBirthday.text = SimpleDateFormat("dd MMMM yyyy",
                    Locale.getDefault()).format(birthday) +
                    " (${now - calendar.get(Calendar.YEAR)})"
            }

            userData.email?.let { email ->
                llProfileEmail.visibility = View.VISIBLE
                tvProfileEmail.text = email
            }

            userData.phoneNumber?.let { phoneNumber ->
                llProfilePhone.visibility = View.VISIBLE
                tvProfilePhone.text = phoneNumber
            }

            userData.about?.let { about ->
                llProfileAbout.visibility = View.VISIBLE
                tvProfileAbout.text = about
            }
        }
    }

    override fun loadingUserData(state: Boolean) {
        view?.srlProfileData?.isRefreshing = state
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        if (view != null) {
            return view
        }

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.bProfileSettings.setOnClickListener {
            launchFragment(settingsFragment)
        }

        view.bProfileEdit.setOnClickListener {
            launchFragment(editProfileFragment)
        }

        view.srlProfileData.setOnRefreshListener {
            presenter.loadUserData()
        }

        return view
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

        presenter.loadUserData()
    }

}
