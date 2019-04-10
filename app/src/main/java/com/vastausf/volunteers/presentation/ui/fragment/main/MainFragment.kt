package com.vastausf.volunteers.presentation.ui.fragment.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.squareup.picasso.Picasso
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.recycler.EventsRecyclerViewAdapter
import com.vastausf.volunteers.adapter.recycler.GroupsRecyclerViewAdapter
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.model.volunteers.data.EventDataFull
import com.vastausf.volunteers.model.volunteers.data.GroupDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.view.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: MainFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    private val eventList = mutableListOf<EventDataFull>()
    private val groupList = mutableListOf<GroupDataFull>()

    override fun bindEventList(events: List<EventDataFull>) {
        eventList.clear()
        view?.rvEventList?.adapter?.notifyItemRangeRemoved(0, events.size)

        eventList.addAll(events)
        view?.rvEventList?.adapter?.notifyItemRangeInserted(0, events.size)
    }

    override fun bindMoreEventList(events: List<EventDataFull>) {
        eventList.addAll(events)
        view?.rvEventList?.adapter?.let {
            it.notifyItemRangeInserted(it.itemCount, events.size)
        }
    }

    override fun eventsLoadState(state: Boolean) {
        view?.srlEventList?.isRefreshing = state
    }

    override fun bindGroupList(groups: List<GroupDataFull>) {
        groupList.clear()
        view?.rvGroupList?.adapter?.notifyItemRangeRemoved(0, groups.size)

        groupList.addAll(groups)
        view?.rvGroupList?.adapter?.notifyItemRangeInserted(0, groups.size)
    }

    override fun bindMoreGroupList(groups: List<GroupDataFull>) {
        groupList.addAll(groups)
        view?.rvGroupList?.adapter?.let {
            it.notifyItemRangeInserted(it.itemCount, groups.size)
        }
    }

    override fun groupsLoadState(state: Boolean) {
        view?.srlGroupList?.isRefreshing = state
    }

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        view.bLogOut.setOnClickListener {
            presenter.onLogOut()
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

        view.rvEventList.apply {
            adapter = EventsRecyclerViewAdapter(picasso,
                eventList,
                onItemClick = {
                    showToast(it)
                },
                onLikeClick = { eventId, currentState ->
                    presenter.likeEvent(eventId, !currentState)
                },
                onJoinClick = { eventId, currentState ->
                    presenter.joinEvent(eventId, !currentState)
                },
                onLinkClick = { link ->
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(if (!link.startsWith("http://") && !link.startsWith("https://")) "http://$link" else link)).apply {
                        startActivity(this)
                    }
                },
                onCreateLastElement = {
                    presenter.loadMoreEventList(it)
                })
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        view.rvGroupList.apply {
            adapter = GroupsRecyclerViewAdapter(picasso, groupList,
                onItemClick = {
                    showToast(it)
                },
                onCreateLastElement = {
                    presenter.loadMoreGroupList(it)
                })
            layoutManager = LinearLayoutManager(this@MainFragment.context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        view.srlEventList.setOnRefreshListener {
            presenter.loadEventList()
        }

        view.srlGroupList.setOnRefreshListener {
            presenter.loadGroupList()
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

    override fun onStart() {
        super.onStart()

        presenter.onViewCreated()
    }

    override fun loadingProgress(state: Boolean) {
        view?.progressBar?.visibility = if (state) View.VISIBLE else View.GONE
    }

}
