package com.vastausf.volunteers.presentation.ui.fragment.registration

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.adapter.pager.registration.pages.RegistrationTabFragment3
import com.vastausf.volunteers.adapter.pager.registration.RegistrationPagerAdapter
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment(), RegistrationFragmentView, RegistrationTabFragment3.RegistrationListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: RegistrationFragmentPresenter

    override fun onSignUpClick() {
        val registrationPagerAdapter = (vpRegistrationPager.adapter as RegistrationPagerAdapter)

        val registrationTabFragment0 = registrationPagerAdapter.pages[0].view
        val registrationTabFragment1 = registrationPagerAdapter.pages[1].view
        val registrationTabFragment2 = registrationPagerAdapter.pages[2].view
        val registrationTabFragment3 = registrationPagerAdapter.pages[3].view

        if (registrationTabFragment0 == null || registrationTabFragment1 == null || registrationTabFragment2 == null || registrationTabFragment3 == null)
            throw IllegalStateException("Fragments not found.")

        val registrationLogin =
            registrationTabFragment0.findViewById<TextInputEditText>(R.id.tietRegistrationLogin)
                .text.toString()
        val registrationPassword =
            registrationTabFragment0.findViewById<TextInputEditText>(R.id.tietRegistrationPassword)
                .text.toString()
        val registrationPasswordCheck =
            registrationTabFragment0.findViewById<TextInputEditText>(R.id.tietRegistrationPasswordCheck)
                .text.toString()

        val firstName = registrationTabFragment1.findViewById<TextInputEditText>(R.id.tietFirstName)
            .text.toString()
        val lastName = registrationTabFragment1.findViewById<TextInputEditText>(R.id.tietLastName)
            .text.toString()
        val middleName =
            registrationTabFragment1.findViewById<TextInputEditText>(R.id.tietMiddleName)
                .text.toString()

        val birthday = registrationTabFragment2.findViewById<CalendarView>(R.id.cvUserBirthday)
            .date

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
            .setPositiveButton(R.string.enter) { dialog, which ->
                presenter.onAccountEnter()
            }
            .setNegativeButton(R.string.no) { dialog, which ->
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
                adapter = RegistrationPagerAdapter(it)
                offscreenPageLimit = adapter?.count ?: 5
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