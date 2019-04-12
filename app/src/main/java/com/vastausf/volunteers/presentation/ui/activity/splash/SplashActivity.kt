package com.vastausf.volunteers.presentation.ui.activity.splash

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivity
import javax.inject.Inject

class SplashActivity : BaseActivity(), SplashActivityView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: SplashActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            DaggerActivityComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)
        super.onCreate(savedInstanceState)

        presenter.onCreate()
    }
}
