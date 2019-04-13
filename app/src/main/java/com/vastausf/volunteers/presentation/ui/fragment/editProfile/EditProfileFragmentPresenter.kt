package com.vastausf.volunteers.presentation.ui.fragment.editProfile

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.data.UserDataEdit
import com.vastausf.volunteers.model.volunteers.data.UserProfileEditI
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class EditProfileFragmentPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApiClient: VolunteersApiClient
) : BaseFragmentPresenter<EditProfileFragmentView>() {

    fun loadProfile() {
        volunteersApiClient
            .userProfile()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onLoadProfileSuccess, ::onLoadProfileError)
            .unsubscribeOnDestroy()
    }

    private fun onLoadProfileSuccess(userProfileI: UserProfileI) {
        viewState.bindProfile(
            UserDataEdit(
                userProfileI.data.firstName,
                userProfileI.data.lastName,
                userProfileI.data.middleName,
                userProfileI.data.about,
                userProfileI.data.phoneNumber,
                userProfileI.data.image,
                userProfileI.data.email,
                userProfileI.data.link
            )
        )
    }

    private fun onLoadProfileError(error: Throwable) {
        viewState.showToast(error::class.java.name)
    }


    fun editProfile(userDataEdit: UserDataEdit) {
        volunteersApiClient
            .editProfile(userDataEdit)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::onEditProfileSuccess, ::onEditProfileError)
            .unsubscribeOnDestroy()
    }

    private fun onEditProfileSuccess(userProfileEditI: UserProfileEditI) {
        viewState.showToast(volunteersApplication.getString(R.string.success))
        viewState.goBack()
    }

    private fun onEditProfileError(error: Throwable) {
        viewState.showToast(error::class.java.name)
    }

}