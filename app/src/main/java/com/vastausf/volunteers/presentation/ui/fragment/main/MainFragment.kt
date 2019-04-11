package com.vastausf.volunteers.presentation.ui.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import com.vastausf.volunteers.presentation.ui.fragment.events.EventsFragment
import com.vastausf.volunteers.presentation.ui.fragment.groups.GroupsFragment
import com.vastausf.volunteers.presentation.ui.fragment.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import kotlinx.android.synthetic.main.item_event.view.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: MainFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    private val eventsFragment by lazy {
        EventsFragment()
    }
    private val profileFragment by lazy {
        ProfileFragment()
    }
    private val groupsFragment by lazy {
        GroupsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.iEvents -> {
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.container, eventsFragment)
                    }?.commit()
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.iProfile -> {
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.container, profileFragment)
                    }?.commit()

                    return@setOnNavigationItemSelectedListener true
                }

                R.id.iGroups -> {
                    fragmentManager?.beginTransaction()?.apply {
                        replace(R.id.container, groupsFragment)
                    }?.commit()

                    return@setOnNavigationItemSelectedListener true
                }

                else -> throw IllegalAccessException("Illegal menu item")
            }
        }

        view.bnvMain.selectedItemId = R.id.iEvents

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

    override fun onStart() {
        super.onStart()

        presenter.onViewCreated()
    }

}
