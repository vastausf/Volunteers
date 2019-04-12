package com.vastausf.volunteers.presentation.ui.activity.base

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragment

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

    override fun launchFragment(fragment: BaseFragment, finish: Boolean) {
        supportFragmentManager?.beginTransaction()
            ?.apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace(R.id.fragmentContainer, fragment)
                if (!finish) addToBackStack(fragment.tag)
            }
            ?.commit()

    }

    override fun showToast(text: Any) {
        Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT)
            .show()
    }

}
