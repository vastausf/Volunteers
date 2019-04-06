package com.vastausf.volunteers.presentation.login

import android.content.SharedPreferences
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.presentation.base.BasePresenter
import javax.inject.Inject

class LoginPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApplicationSharedPreferences: SharedPreferences
) : BasePresenter<LoginView>() {

}