package com.vastausf.volunteers.presentation.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vastausf.volunteers.VolunteersApplication

@SuppressLint("Registered")
abstract class BaseActivity : MvpAppCompatActivity(), BaseView {

    val volunteersApplication: VolunteersApplication by lazy {
        application as VolunteersApplication
    }

    override fun startActivity(activity: Class<out MvpAppCompatActivity>,
        bundle: Bundle?,
        finish: Boolean) {
        Intent(this, activity).apply {
            bundle?.let {
                putExtras(it)
            }

            startActivity(this)
        }

        if (finish) finishAffinity()
    }

    override fun showToast(text: Any) {
        Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT)
            .show()
    }

    override fun loadingProgress(state: Boolean) {

    }

}