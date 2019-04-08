package com.vastausf.volunteers.presentation.ui.fragment.main

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.events.EventsRecyclerViewAdapter
import com.vastausf.volunteers.adapter.events.EventsRecyclerViewItem
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: MainFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.bLogOut.setOnClickListener {
            presenter.onLogOut()
        }

        val item = EventsRecyclerViewItem(
            1,
            "Some title",
            "Saransk, School 35",
            1554757684797,
            3600000,
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum finibus ut massa nec vestibulum. Fusce porta varius pulvinar. In eget scelerisque ipsum. Vivamus porta lacus felis. Pellentesque sed lacinia erat. Mauris suscipit vel dolor eget bibendum. Nullam et libero eget odio molestie lobortis. In pretium, libero eget luctus ornare, ipsum metus porttitor metus, id pretium lectus mauris sed nisi...",
            13,
            true,
            84,
            true,
            "https://www.google.ru/",
            "https://static.addtoany.com/images/dracaena-cinnabari.jpg"
        )

        view.rvEventList.apply {
            adapter = EventsRecyclerViewAdapter(listOf(
                item, item, item, item, item
            ),
                onLikeClick = {
                    showToast(it)
                },
                onJoinClick = {
                    showToast(it)
                },
                onLinkClick = {
                    showToast(it)
                })
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        view.bnvMain.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.iEvents -> {
                    view.clEvents.visibility = View.VISIBLE
                    view.clProfile.visibility = View.GONE
                    view.clGroups.visibility = View.GONE

                    return@setOnNavigationItemSelectedListener true
                }

                R.id.iProfile -> {
                    view.clEvents.visibility = View.GONE
                    view.clProfile.visibility = View.VISIBLE
                    view.clGroups.visibility = View.GONE

                    return@setOnNavigationItemSelectedListener true
                }

                R.id.iGroups -> {
                    view.clEvents.visibility = View.GONE
                    view.clProfile.visibility = View.GONE
                    view.clGroups.visibility = View.VISIBLE

                    return@setOnNavigationItemSelectedListener true
                }

                else -> throw IllegalAccessException("Illegal menu item")
            }
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

        bindViewMethods()
    }

    private fun bindViewMethods() {

    }

}
