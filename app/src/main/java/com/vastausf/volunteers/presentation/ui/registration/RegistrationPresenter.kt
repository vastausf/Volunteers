package com.vastausf.volunteers.presentation.ui.registration

import com.arellomobile.mvp.InjectViewState
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.HttpStatusCodes
import com.vastausf.volunteers.model.volunteers.VolunteersApiClient
import com.vastausf.volunteers.model.volunteers.VolunteersTokenStore
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.presentation.ui.base.BasePresenter
import com.vastausf.volunteers.presentation.ui.main.MainActivity
import com.vastausf.volunteers.utils.getHashSHA256
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalTime
import java.time.Period
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersApiClient: VolunteersApiClient,
    private val tokenStore: VolunteersTokenStore
) : BasePresenter<RegistrationView>() {
    lateinit var login: String
    lateinit var passwordHash: String

    lateinit var accessToken: String
    lateinit var refreshToken: String

    fun registration(
        login: String,
        password: String,
        passwordCheck: String,
        firstName: String,
        lastName: String,
        middleName: String,
        birthday: Long
    ) {
        if (firstName.isNotEmpty() && lastName.isNotEmpty() && middleName.isNotEmpty())
            if (password == passwordCheck)
                if (Regex(volunteersApplication.getString(R.string.login_regex)).matches(login) && Regex(volunteersApplication.getString(R.string.password_regex)).matches(password)) {
                    viewState.loadingProgress(true)

                    this.login = login
                    this.passwordHash = password.getHashSHA256()

                    volunteersApiClient
                        .userCreate(
                            login,
                            password.getHashSHA256(),
                            firstName,
                            lastName,
                            middleName,
                            birthday)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doFinally {
                            viewState.loadingProgress(false)
                        }
                        .subscribe(::onRegistrationSuccess, ::onRegistrationError)
                        .unsubscribeOnDestroy()
                } else viewState.showToast(volunteersApplication.getString(R.string.incorrect_login_or_password))
             else viewState.showToast(volunteersApplication.getString(R.string.passwords_do_not_match))
         else viewState.showToast(volunteersApplication.getString(R.string.fill_all_fields))
    }

    private fun onRegistrationSuccess(userRegistrationI: UserRegistrationI) {
        accessToken = userRegistrationI.token

        refreshToken = userRegistrationI.token.split(":")[0] + login + passwordHash

        viewState.showSuccessRegistrationDialog()
    }

    private fun onRegistrationError(error: Throwable) {
        when (error) {
            is HttpException ->
                when (error.code()) {
                    HttpStatusCodes.CONFLICT -> {
                        viewState.showToast(volunteersApplication.getString(R.string.login_already_taken))
                    }
                }
        }

        viewState.showToast(error::class.java.name)
    }

    fun onAccountEnter() {
        tokenStore.accessToken = accessToken
        tokenStore.refreshToken = refreshToken

        viewState.startActivity(MainActivity::class.java, finish = true)
    }

}