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
import com.vastausf.volunteers.presentation.fragment.RegistrationTabFragment4
import com.vastausf.volunteers.presentation.pager.RegistrationPagerAdapter
import com.vastausf.volunteers.presentation.ui.base.BaseActivity
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.activity_registration.*
import java.lang.IllegalStateException
import javax.inject.Inject

class RegistrationActivity : BaseActivity(), RegistrationView, RegistrationTabFragment4.RegistrationListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: RegistrationPresenter

    override fun onSignUpClick() {
        val fragment1 = supportFragmentManager
            .findFragmentByTag("android:switcher:${R.id.vpRegistrationPager}:0")
        val fragment2 = supportFragmentManager
            .findFragmentByTag("android:switcher:${R.id.vpRegistrationPager}:1")
        val fragment3 = supportFragmentManager
            .findFragmentByTag("android:switcher:${R.id.vpRegistrationPager}:2")
        val fragment4 = supportFragmentManager
            .findFragmentByTag("android:switcher:${R.id.vpRegistrationPager}:3")

        val fragmentView1 = fragment1?.view
        val fragmentView2 = fragment2?.view
        val fragmentView3 = fragment3?.view
        val fragmentView4 = fragment4?.view

        if (fragmentView1 == null || fragmentView2 == null || fragmentView3 == null || fragmentView4 == null)
            throw IllegalStateException("Fragments not found.")

        val registrationLogin = fragmentView1.findViewById<TextInputEditText>(R.id.tietRegistrationLogin).text.toString()
        val registrationPassword = fragmentView1.findViewById<TextInputEditText>(R.id.tietRegistrationPassword).text.toString()
        val registrationPasswordCheck = fragmentView1.findViewById<TextInputEditText>(R.id.tietRegistrationPasswordCheck).text.toString()

        val firstName = fragmentView2.findViewById<TextInputEditText>(R.id.tietFirstName).text.toString()
        val lastName = fragmentView2.findViewById<TextInputEditText>(R.id.tietLastName).text.toString()
        val middleName = fragmentView2.findViewById<TextInputEditText>(R.id.tietMiddleName).text.toString()

        val birthday = fragmentView3.findViewById<CalendarView>(R.id.cvUserBirthday).date

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