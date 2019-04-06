package com.vastausf.volunteers.presentation.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.di.activity.DaggerActivityComponent
import com.vastausf.volunteers.presentation.base.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var picasso: Picasso

    private val volunteersApplication: VolunteersApplication by lazy {
        application as VolunteersApplication
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null)
            DaggerActivityComponent
                .builder()
                .applicationComponent(volunteersApplication.applicationComponent)
                .build()
                .inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        presenter.onViewCreated()
    }

}
