package com.vastausf.volunteers.model.volunteers

import android.content.SharedPreferences
import com.vastausf.volunteers.model.ApplicationDataStore
import com.vastausf.volunteers.utils.getHashSHA256
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class VolunteersTokenStore
@Inject
constructor(
    private val volunteersApplicationSharedPreferences: SharedPreferences,
    private val applicationDataStore: ApplicationDataStore
) {
    private val accessTokenSharedPreferences = "volunteersServerAccessToken"
    var accessToken: String
        get() {
            return volunteersApplicationSharedPreferences
                .getString(accessTokenSharedPreferences, null) ?: ""
        }
        set(value) {
            volunteersApplicationSharedPreferences
                .edit()
                .putString(accessTokenSharedPreferences, value)
                .apply()
        }

    private val volunteersServerRefreshToken = "volunteersServerRefreshToken"
    var refreshToken: String
        get() {
            println(accessToken)
            val userId = accessToken.split(":")[0]
            return "$userId:${("$userId${applicationDataStore.login}" +
                "${applicationDataStore.passwordHash}" +
                SimpleDateFormat("yyyyMMddHHmm", Locale.getDefault()).format(Date()))
                .getHashSHA256()}"
        }
        set(value) {
            volunteersApplicationSharedPreferences
                .edit()
                .putString(volunteersServerRefreshToken, value)
                .apply()
        }

    fun removeTokens() {
        volunteersApplicationSharedPreferences.edit()
            .remove(accessTokenSharedPreferences)
            .apply()
        volunteersApplicationSharedPreferences.edit()
            .remove(volunteersServerRefreshToken)
            .apply()
    }
}
