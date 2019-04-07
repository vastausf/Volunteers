package com.vastausf.volunteers.model

import android.content.SharedPreferences
import javax.inject.Inject

class ApplicationDataStore
@Inject
constructor(
    private val volunteersApplicationSharedPreferences: SharedPreferences
) {

    private val loginSharedPreferences = "volunteersLogin"
    var login: String?
        get() {
            return volunteersApplicationSharedPreferences
                .getString(loginSharedPreferences, null)
        }
        set(value) {
            volunteersApplicationSharedPreferences
                .edit()
                .putString(loginSharedPreferences, value)
                .apply()
        }

    private val passwordHashSharedPreferences = "volunteersPasswordHash"
    var passwordHash: String?
        get() {
            return volunteersApplicationSharedPreferences
                .getString(passwordHashSharedPreferences, null)
        }
        set(value) {
            volunteersApplicationSharedPreferences
                .edit()
                .putString(passwordHashSharedPreferences, value)
                .apply()
        }

    fun removeData() {
        volunteersApplicationSharedPreferences.edit()
            .remove(loginSharedPreferences)
            .apply()
        volunteersApplicationSharedPreferences.edit()
            .remove(passwordHashSharedPreferences)
            .apply()
    }
}