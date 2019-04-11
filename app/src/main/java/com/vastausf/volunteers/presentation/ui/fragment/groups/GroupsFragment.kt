package com.vastausf.volunteers.presentation.ui.fragment.groups

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
import com.vastausf.volunteers.adapter.recycler.GroupsRecyclerViewAdapter
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.model.volunteers.data.GroupDataFull
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_groups.view.*
import javax.inject.Inject

class GroupsFragment: BaseFragment(), GroupsFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: GroupsFragmentPresenter

    @Inject
    lateinit var picasso: Picasso

    private val groupList = mutableListOf<GroupDataFull>()

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_groups, container, false)

        view.rvGroupList.apply {
            adapter = GroupsRecyclerViewAdapter(picasso, groupList,
                onItemClick = {
                    showToast(it)
                },
                onCreateLastElement = {
                    presenter.loadMoreGroupList(it)
                })
            layoutManager = LinearLayoutManager(this@GroupsFragment.context)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }

        view.srlGroupList.setOnRefreshListener {
            presenter.loadGroupList()
        }

        return view
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

    override fun groupsLoadState(state: Boolean) {
        view?.srlGroupList?.isRefreshing = state
    }

    override fun onStart() {
        super.onStart()

        presenter.onViewCreated()
    }

}