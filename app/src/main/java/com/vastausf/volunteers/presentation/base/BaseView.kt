package com.vastausf.volunteers.presentation.base

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView

interface BaseView: MvpView {

    fun startActivity(activity: Class<out MvpAppCompatActivity>, bundle: Bundle? = null, finish: Boolean = false)

    fun showToast(text: Any)

}