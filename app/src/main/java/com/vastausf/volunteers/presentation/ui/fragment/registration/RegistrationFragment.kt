package com.vastausf.volunteers.presentation.ui.fragment.registration

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.pager.registration.RegistrationPagerAdapter
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment3
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import kotlinx.android.synthetic.main.fragment_registration_tab_0.view.*
import kotlinx.android.synthetic.main.fragment_registration_tab_1.view.*
import kotlinx.android.synthetic.main.fragment_registration_tab_2.view.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment(), RegistrationFragmentView,
    RegistrationTabFragment3.RegistrationListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: RegistrationFragmentPresenter

    override fun onSignUpClick() {
        val registrationPagerAdapter = (vpRegistrationPager.adapter as RegistrationPagerAdapter)

        val (registrationTabFragment0,
            registrationTabFragment1,
            registrationTabFragment2) = registrationPagerAdapter.pages.mapNotNull { it.view }

        val registrationLogin =
            registrationTabFragment0.tietRegistrationLogin.text.toString()
        val registrationPassword =
            registrationTabFragment0.tietRegistrationPassword.text.toString()
        val registrationPasswordCheck =
            registrationTabFragment0.tietRegistrationPasswordCheck.text.toString()

        val firstName =
            registrationTabFragment1.tietFirstName.text.toString()
        val lastName =
            registrationTabFragment1.tietLastName.text.toString()
        val middleName =
            registrationTabFragment1.tietMiddleName.text.toString()

        val birthday =
            registrationTabFragment2.cvUserBirthday.date

        presenter.registration(
            registrationLogin.trimAllSpaces(),
            registrationPassword.trimAllSpaces(),
            registrationPasswordCheck.trimAllSpaces(),
            firstName.trimAllSpaces(),
            lastName.trimAllSpaces(),
            middleName.trimAllSpaces(),
            birthday
        )
    }

    override fun loadingProgress(state: Boolean) {
        progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun showSuccessRegistrationDialog() {
        AlertDialog
            .Builder(this.context)
            .setMessage(R.string.account_created)
            .setPositiveButton(R.string.enter) { _, _ ->
                presenter.onAccountEnter()
            }
            .setNegativeButton(R.string.no) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        view.vpRegistrationPager.apply {
            childFragmentManager.let {
                val registrationPagerAdapter = RegistrationPagerAdapter(it)
                adapter = registrationPagerAdapter
                offscreenPageLimit = registrationPagerAdapter.pages.size
            }
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
    }

}
