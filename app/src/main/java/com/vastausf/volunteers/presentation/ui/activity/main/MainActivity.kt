package com.vastausf.volunteers.presentation.ui.activity.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivity
import com.vastausf.volunteers.presentation.ui.fragment.main.MainFragment
import javax.inject.Inject

class MainActivity : BaseActivity(), MainActivityView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerActivityComponent
            .builder()
            .applicationComponent(volunteersApplication.applicationComponent)
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)

        supportFragmentManager.beginTransaction()
            .apply {
                add(R.id.fragmentContainer, MainFragment())
            }
            .commit()
    }

}
