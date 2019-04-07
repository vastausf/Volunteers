package com.vastausf.volunteers.model.volunteers

import android.content.SharedPreferences
import javax.inject.Inject

class VolunteersTokenStore
@Inject
constructor(
    private val volunteersApplicationSharedPreferences: SharedPreferences
) {

    private val accessTokenSharedPreferences = "volunteersServerAccessToken"
    var accessToken: String?
        get() {
            return volunteersApplicationSharedPreferences
                .getString(accessTokenSharedPreferences, null)
        }
        set(value) {
            volunteersApplicationSharedPreferences
                .edit()
                .putString(accessTokenSharedPreferences, value)
                .apply()
        }

    private val volunteersServerRefreshToken = "volunteersServerRefreshToken"
    var refreshToken: String?
        get() {
            return volunteersApplicationSharedPreferences
                .getString(volunteersServerRefreshToken, null)
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