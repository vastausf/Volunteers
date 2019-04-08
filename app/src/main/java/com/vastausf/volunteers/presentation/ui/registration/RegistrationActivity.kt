package com.vastausf.volunteers.presentation.ui.registration

import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.view.View
import android.widget.CalendarView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment3
import com.vastausf.volunteers.presentation.pager.RegistrationPagerAdapter
import com.vastausf.volunteers.presentation.ui.base.BaseActivity
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.activity_registration.*
import javax.inject.Inject

class RegistrationActivity : BaseActivity(), RegistrationView, RegistrationTabFragment3.RegistrationListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: RegistrationPresenter

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
            .Builder(this)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            DaggerActivityComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)
        }
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registration)

        vpRegistrationPager.apply {
            adapter = RegistrationPagerAdapter(supportFragmentManager)
            offscreenPageLimit = adapter?.count ?: 5
        }
    }

}