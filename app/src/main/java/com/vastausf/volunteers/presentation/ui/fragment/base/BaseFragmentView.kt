package com.vastausf.volunteers.presentation.ui.fragment.base

import com.arellomobile.mvp.MvpView
import com.vastausf.volunteers.R
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivity

interface BaseFragmentView : MvpView {

    fun launchActivity(activity: BaseActivity, finish: Boolean = false)

    fun launchFragment(fragment: BaseFragment, finish: Boolean = false, container: Int = R.id.fragmentContainer)

    fun replaceFragment(fragment: BaseFragment, finish: Boolean = false, container: Int = R.id.fragmentContainer)

    fun goBack()

    fun showToast(text: Any)

    fun loadingProgress(state: Boolean)

}
