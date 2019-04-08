package com.vastausf.volunteers.presentation.ui.fragment.base

import com.arellomobile.mvp.MvpView

interface BaseFragmentView : MvpView {

    fun launchFragment(fragment: BaseFragment, finish: Boolean = false)

    fun showToast(text: Any)

    fun loadingProgress(state: Boolean)

}