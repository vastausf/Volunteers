package com.vastausf.volunteers.presentation.ui.fragment.login

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

class LoginFragment : BaseFragment(), LoginFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: LoginFragmentPresenter

    @Inject
    lateinit var applicationDataStore: ApplicationDataStore

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        view.bSignIn.setOnClickListener {
            presenter.onSignIn(
                tietLogin.text.toString().trimAllSpaces(),
                tietPassword.text.toString().trimAllSpaces())
        }

        view.tvRegistration.setOnClickListener {
            presenter.onRegistration()
        }

        view.tietPassword.apply {
            val tf = typeface
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            typeface = tf
        }

        return view
    }

    override fun onStart() {
        super.onStart()

        val login = applicationDataStore.login

        if (login != null) {
            tietLogin.setText(login)
            tietPassword.requestFocus()
        }
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

    override fun loadingProgress(state: Boolean) {
        bSignIn.isEnabled = !state
        tietLogin.isEnabled = !state
        tietPassword.isEnabled = !state

        progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}