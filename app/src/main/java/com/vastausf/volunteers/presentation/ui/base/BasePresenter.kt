package com.vastausf.volunteers.presentation.ui.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val compositeDisposable = CompositeDisposable()

    protected fun Disposable.unsubscribeOnDestroy() =
        compositeDisposable.add(this)

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable.clear()
    }
}