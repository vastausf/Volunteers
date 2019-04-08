package com.vastausf.volunteers.presentation.ui.activity.base

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vastausf.volunteers.VolunteersApplication

@SuppressLint("Registered")
abstract class BaseActivity : MvpAppCompatActivity(), BaseActivityView {

    val volunteersApplication: VolunteersApplication by lazy {
        application as VolunteersApplication
    }

    override fun launchActivity(activity: Class<out BaseActivity>, finish: Boolean) {
        Intent(this, activity).apply {
            startActivity(this@apply)
            if (finish)
                finishAffinity()
        }
    }

    override fun showToast(text: Any) {
        Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
    }

}