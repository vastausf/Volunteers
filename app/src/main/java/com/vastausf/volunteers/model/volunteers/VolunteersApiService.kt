package com.vastausf.volunteers.model.volunteers

import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginI
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginO
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationO
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface VolunteersApiService {

    @POST("auth/token/create/byLoginAndPassword")
    fun tokenCreateByLogin(
        @Header("ContentType") contentType: String,
        @Body body: TokenCreateByLoginO
    ): Single<TokenCreateByLoginI>

    @POST("users/create")
    fun userCreate(
        @Header("ContentType") contentType: String,
        @Body body: UserRegistrationO
    ): Single<UserRegistrationI>

    @POST("users/profile/get")
    fun userProfile(
        @Header("ContentType") contentType: String,
        @Header("Access-Token") accessToken: String
    ): Single<UserProfileI>

}