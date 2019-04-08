package com.vastausf.volunteers.model.volunteers

import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginO
import com.vastausf.volunteers.model.volunteers.data.UserDataShort
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationO
import javax.inject.Inject

class VolunteersApiClient
@Inject constructor(
    private val volunteersApiService: VolunteersApiService,
    private val volunteersTokenStore: VolunteersTokenStore
) {

    private val contentType: String =
        "application/json"

    fun createTokenByLogin(
        login: String,
        password: String
    ) =
        volunteersApiService
            .tokenCreateByLogin(contentType,
                TokenCreateByLoginO(login, password))

    fun userCreate(
        login: String,
        password: String,
        firstName: String,
        lastName: String,
        middleName: String,
        birthday: Long,
        about: String? = null,
        phoneNumber: String? = null,
        image: String? = null,
        email: String? = null,
        link: String? = null
    ) =
        volunteersApiService
            .userCreate(contentType,
                UserRegistrationO(login, password,
                    UserDataShort(firstName, lastName, middleName, birthday, about, phoneNumber, image, email, link)))

    fun userProfile() {
        volunteersApiService.userProfile(
            contentType,
            volunteersTokenStore.accessToken)
    }

}