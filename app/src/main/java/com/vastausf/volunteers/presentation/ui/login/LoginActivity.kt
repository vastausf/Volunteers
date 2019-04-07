package com.vastausf.volunteers.presentation.ui.login

import android.os.Bundle
import android.text.InputType
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.presentation.ui.base.BaseActivity
import com.vastausf.volunteers.utils.trimAllSpaces
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: LoginPresenter

    @Inject
    lateinit var applicationDataStore: ApplicationDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            DaggerActivityComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val login = applicationDataStore.login

        if (login != null) {
            tietLogin.setText(login)
            tietPassword.requestFocus()
        }

        bindViewMethods()
    }

    private fun bindViewMethods() {
        bSignIn.setOnClickListener {
            presenter.onSignIn(
                tietLogin.text.toString().trimAllSpaces(),
                tietPassword.text.toString().trimAllSpaces())
        }

        tvRegistration.setOnClickListener {
            presenter.onRegistration()
        }

        tietPassword.apply {
            val tf = typeface
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            typeface = tf
        }
    }

    override fun loadingProgress(state: Boolean) {
        bSignIn.isEnabled = !state
        tietLogin.isEnabled = !state
        tietPassword.isEnabled = !state

        progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

}