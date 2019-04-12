package com.vastausf.volunteers.presentation.ui.fragment.newEvent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.vastausf.volunteers.R
import com.vastausf.volunteers.adapter.pager.event.NewEventPagerAdapter
import com.vastausf.volunteers.di.fragment.DaggerFragmentComponent
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_event.view.*
import javax.inject.Inject

class NewEventFragment : BaseFragment(), NewEventFragmentView {

    @Inject
    @get:ProvidePresenter
    @field:InjectPresenter
    lateinit var presenter: NewEventFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_new_event, container, false)

        view.vpNewEvent.apply {
            val newEventPager = NewEventPagerAdapter(childFragmentManager)
            adapter = newEventPager
            offscreenPageLimit = newEventPager.pages.size
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

}
