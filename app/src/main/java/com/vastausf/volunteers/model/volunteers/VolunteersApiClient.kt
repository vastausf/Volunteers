package com.vastausf.volunteers.model.volunteers

import javax.inject.Inject

class VolunteersApiClient
@Inject constructor(
    private val volunteersApiService: VolunteersApiService,
    private val volunteersTokenStore: VolunteersTokenStore
) {

    private val contentType: String =
        "application/json"

    private val accept: String =
        "application/json"

    fun createTokenByLogin(login: String, password: String) =
        volunteersApiService.tokenCreateByLogin(contentType, accept,
            TokenCreateByLoginO(
                login, password
            ))

}