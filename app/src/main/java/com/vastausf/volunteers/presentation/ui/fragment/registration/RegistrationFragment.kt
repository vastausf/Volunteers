package com.vastausf.volunteers.presentation.ui.fragment.registration

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.pager.RegistrationPagerAdapter
import com.vastausf.volunteers.adapter.pager.pages.FragmentAccept
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.fragment_birthday.*
import kotlinx.android.synthetic.main.fragment_load_image.*
import kotlinx.android.synthetic.main.fragment_login_data.*
import kotlinx.android.synthetic.main.fragment_names.*
import kotlinx.android.synthetic.main.fragment_registration.*
import kotlinx.android.synthetic.main.fragment_registration.view.*
import java.util.Calendar
import javax.inject.Inject

class RegistrationFragment : BaseFragment(), RegistrationFragmentView,
    FragmentAccept.AcceptListener {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: RegistrationFragmentPresenter

    lateinit var registrationPagerAdapter: RegistrationPagerAdapter

    override fun onAccept() {
        val registrationPagerAdapter = (vpRegistration.adapter as RegistrationPagerAdapter)

        val registrationLogin =
            registrationPagerAdapter.registrationFragmentLogin.etLogin.text.toString()
        val registrationPassword =
            registrationPagerAdapter.registrationFragmentLogin.etPassword.text.toString()
        val registrationPasswordCheck =
            registrationPagerAdapter.registrationFragmentLogin.etPasswordCheck.text.toString()

        val firstName =
            registrationPagerAdapter.registrationFragmentNames.etFirstName.text.toString()
        val lastName =
            registrationPagerAdapter.registrationFragmentNames.etLastName.text.toString()
        val middleName =
            registrationPagerAdapter.registrationFragmentNames.etMiddleName.text.toString()

        val birthday =
            Calendar.getInstance()
                .apply {
                    val datePicker =
                        registrationPagerAdapter.registrationFragmentBirthday.dpBirthday
                    set(datePicker.year, datePicker.month, datePicker.dayOfMonth)
                }
                .time.time

        val image =
            registrationPagerAdapter.loadImageFragment.etImageLink.text.toString()
                .ifEmpty { null }

        presenter.registration(
            registrationLogin.trimAllSpaces(),
            registrationPassword.trimAllSpaces(),
            registrationPasswordCheck.trimAllSpaces(),
            firstName.trimAllSpaces(),
            lastName.trimAllSpaces(),
            middleName.trimAllSpaces(),
            birthday,
            image
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
            .setNegativeButton(R.string.no) { _, _ ->
                fragmentManager?.popBackStack()
            }
            .setOnCancelListener {
                fragmentManager?.popBackStack()
            }
            .create()
            .show()
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_registration, container, false)

        registrationPagerAdapter = RegistrationPagerAdapter(childFragmentManager)

        view.vpRegistration.apply {
            adapter = registrationPagerAdapter
            offscreenPageLimit = registrationPagerAdapter.pages.size
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
