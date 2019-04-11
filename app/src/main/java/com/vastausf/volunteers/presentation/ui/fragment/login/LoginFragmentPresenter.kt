package com.vastausf.volunteers.presentation.ui.fragment.login

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.model.HttpStatusCodes
import com.vastausf.volunteers.model.volunteers.Unauthorized
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginI
import com.vastausf.volunteers.presentation.ui.fragment.base.BaseFragmentPresenter
import com.vastausf.volunteers.presentation.ui.fragment.main.MainFragment
import com.vastausf.volunteers.presentation.ui.fragment.registration.RegistrationFragment
import com.vastausf.volunteers.utils.getHashSHA256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

@InjectViewState
class LoginFragmentPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApiClient: VolunteersApiClient,
    private val volunteersTokenStore: VolunteersTokenStore,
    private val applicationDataStore: ApplicationDataStore
) : BaseFragmentPresenter<LoginFragmentView>() {

    fun onSignIn(login: String, password: String) {
        val passwordHash = password.getHashSHA256()

        if (Regex(volunteersApplication.getString(R.string.login_regex)).matches(login) && Regex(
                volunteersApplication.getString(R.string.password_regex)).matches(password)) {
            applicationDataStore.login = login
            applicationDataStore.passwordHash = passwordHash
            viewState.loadingProgress(true)

            volunteersApiClient.createTokenByLogin(login, passwordHash)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally {
                    viewState.loadingProgress(false)
                }
                .subscribe(::onLoginSuccess, ::onLoginError)
                .unsubscribeOnDestroy()
        } else {
            viewState.showToast(volunteersApplication.getString(R.string.incorrect_login_or_password))
        }
    }

    private fun onLoginSuccess(tokenCreateByLoginI: TokenCreateByLoginI) {
        volunteersTokenStore.accessToken = tokenCreateByLoginI.token
        volunteersTokenStore.refreshToken =
            tokenCreateByLoginI.token.split(":")[0] +
                applicationDataStore.login + applicationDataStore.passwordHash

        viewState.launchFragment(MainFragment(), finish = true)
    }

    private fun onLoginError(error: Throwable) {
        when (error) {
            is HttpException ->
                when (error.code()) {
                    HttpStatusCodes.UNAUTHORIZED -> {
                        viewState.showToast(R.string.incorrect_login_or_password)
                    }

                    else -> {
                        viewState.showToast(error.response().body().toString())
                    }
                }

            is Unauthorized ->
                viewState.showToast(volunteersApplication.getString(R.string.incorrect_login_or_password))


            else -> {
                error.printStackTrace()
                viewState.showToast(volunteersApplication.getString(R.string.unknown_error))
            }
        }
    }

    fun onRegistration() {
        viewState.launchFragment(RegistrationFragment())
    }

}