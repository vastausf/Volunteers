package com.vastausf.volunteers.presentation.ui.activity.base

import com.arellomobile.mvp.MvpView

interface BaseActivityView : MvpView {

    fun launchActivity(activity: Class<out BaseActivity>, finish: Boolean = false)

    fun showToast(text: Any)

}