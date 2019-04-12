package com.vastausf.volunteers.presentation.ui.activity.login

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivity
import com.vastausf.volunteers.presentation.ui.fragment.login.LoginFragment
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginActivityView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: LoginActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent
            .builder()
            .applicationComponent(volunteersApplication.applicationComponent)
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.login_activity)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragmentContainer, LoginFragment())
            }
            .commit()
    }

}
