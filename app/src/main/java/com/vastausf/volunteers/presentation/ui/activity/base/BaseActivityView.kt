package com.vastausf.volunteers.presentation.ui.activity.base

import com.arellomobile.mvp.MvpView
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment

interface BaseActivityView : MvpView {

    fun launchActivity(activity: Class<out BaseActivity>, finish: Boolean = false)

    fun launchFragment(fragment: BaseFragment, finish: Boolean = false)

    fun showToast(text: Any)

}
