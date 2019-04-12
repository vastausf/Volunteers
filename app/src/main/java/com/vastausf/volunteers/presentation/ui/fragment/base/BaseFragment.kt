package com.vastausf.volunteers.presentation.ui.fragment.base

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.ui.activity.base.BaseActivity

@SuppressLint("Registered")
abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    val volunteersApplication: VolunteersApplication by lazy {
        activity?.application as VolunteersApplication
    }

    override fun launchFragment(fragment: BaseFragment, finish: Boolean, container: Int) {
        fragmentManager?.beginTransaction()
            ?.apply {
                hide(this@BaseFragment)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                add(R.id.fragmentContainer, fragment)
                if (!finish) addToBackStack(fragment.tag)
            }
            ?.commit()
    }

    override fun replaceFragment(fragment: BaseFragment, finish: Boolean, container: Int) {
        fragmentManager?.beginTransaction()
            ?.apply {
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                replace(container, fragment)
            }
            ?.commit()
    }

    override fun showToast(text: Any) {
        Toast.makeText(activity, text.toString(), Toast.LENGTH_SHORT)
            .show()
    }

    override fun launchActivity(activity: BaseActivity, finish: Boolean) {
        Intent(this.activity, activity::class.java).apply {
            this@BaseFragment.activity?.startActivity(this)
            if (finish) this@BaseFragment.activity?.finishAffinity()
        }
    }

    override fun loadingProgress(state: Boolean) {

    }

}
