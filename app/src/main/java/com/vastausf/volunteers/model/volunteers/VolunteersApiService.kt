package com.vastausf.volunteers.model.volunteers

import com.vastausf.volunteers.model.volunteers.data.EventsJoinI
import com.vastausf.volunteers.model.volunteers.data.EventsJoinO
import com.vastausf.volunteers.model.volunteers.data.EventsLikeI
import com.vastausf.volunteers.model.volunteers.data.EventsLikeO
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersO
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersO
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginI
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginO
import com.vastausf.volunteers.model.volunteers.data.UploadImageI
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationO
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

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

    @POST("events/find/list")
    fun eventsFindByParameters(
        @Header("ContentType") contentType: String,
        @Header("Access-Token") accessToken: String,
        @Body body: FindEventsByParametersO
    ): Single<FindEventsByParametersI>

    @POST("events/like")
    fun eventsLike(
        @Header("ContentType") contentType: String,
        @Header("Access-Token") accessToken: String,
        @Body body: EventsLikeO
    ): Single<EventsLikeI>

    @POST("events/join")
    fun eventsJoin(
        @Header("ContentType") contentType: String,
        @Header("Access-Token") accessToken: String,
        @Body body: EventsJoinO
    ): Single<EventsJoinI>

    @POST("groups/find/list")
    fun groupsFindByParameters(
        @Header("ContentType") contentType: String,
        @Header("Access-Token") accessToken: String,
        @Body body: FindGroupsByParametersO
    ): Single<FindGroupsByParametersI>

    @POST("/images")
    fun uploadImage(
        @Header("ContentType") contentType: String,
        @Body image: RequestBody
    ): Single<UploadImageI>

}
