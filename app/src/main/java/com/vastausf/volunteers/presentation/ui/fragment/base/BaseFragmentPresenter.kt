package com.vastausf.volunteers.presentation.ui.fragment.base

import com.arellomobile.mvp.MvpPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragmentPresenter<T : BaseFragmentView> : MvpPresenter<T>() {
    protected val compositeDisposable = CompositeDisposable()

    protected fun Disposable.unsubscribeOnDestroy() =
        compositeDisposable.add(this)

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}
