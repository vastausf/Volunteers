package com.vastausf.volunteers.model.volunteers

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface VolunteersApiService {
    @POST("auth/token/create/byLoginAndPassword")
    fun tokenCreateByLogin(
        @Header("ContentType") contentType: String,
        @Header("Accept") accept: String,
        @Body body: TokenCreateByLoginO
    ): Single<TokenCreateByLoginI>
}