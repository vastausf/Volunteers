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
import com.vastausf.volunteers.presentation.ui.fragment.settings.SettingsFragment
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

class ProfileFragment : BaseFragment(), ProfileFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: ProfileFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    private val settingsFragment = SettingsFragment()

    @SuppressLint("SetTextI18n")
    override fun bindUserData(userData: UserDataFull) {
        view?.apply {
            tvProfileName.text = "${userData.firstName} ${userData.lastName}"
            picasso
                .load(userData.image)
                .into(ivProfileToolbar)
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
            launchFragment(settingsFragment, container = R.id.mainFragmentContainer)
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
