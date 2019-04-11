package com.vastausf.volunteers.presentation.ui.fragment.profile

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ProfileFragmentPresenter
@Inject
constructor(
    private val volunteersApiClient: VolunteersApiClient
) : BaseFragmentPresenter<ProfileFragmentView>() {

    fun onViewCreated() {
        volunteersApiClient
            .userProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally {

            }
            .subscribe(::onUserProfileLoadSuccess, ::onUserProfileLoadError)
            .unsubscribeOnDestroy()
    }

    private fun onUserProfileLoadSuccess(userProfileI: UserProfileI) {
        viewState.bindUserData(userProfileI.data)
    }

    private fun onUserProfileLoadError(error: Throwable) {
        viewState.showToast(R.string.unknown_error)
    }

}