package com.vastausf.volunteers.presentation.ui.fragment.base

import android.annotation.SuppressLint
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication

@SuppressLint("Registered")
abstract class BaseFragment : MvpAppCompatFragment(), BaseFragmentView {

    val volunteersApplication: VolunteersApplication by lazy {
        activity?.application as VolunteersApplication
    }

    override fun launchFragment(fragment: BaseFragment, finish: Boolean) {
        fragmentManager?.beginTransaction()
            ?.apply {
                this.hide(this@BaseFragment)
                setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                add(R.id.fragmentContainer, fragment)
                if (!finish) addToBackStack(fragment.tag)
            }
            ?.commit()

    }

    override fun showToast(text: Any) {
        Toast.makeText(activity, text.toString(), Toast.LENGTH_SHORT)
            .show()
    }

    override fun loadingProgress(state: Boolean) {

    }

}