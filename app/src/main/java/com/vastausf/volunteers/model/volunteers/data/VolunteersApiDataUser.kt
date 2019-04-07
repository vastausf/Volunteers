package com.vastausf.volunteers.model.volunteers.data

import com.squareup.moshi.Json

data class UserRegistrationO(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String,
    @Json(name = "data") val data: UserDataShort
)

data class UserRegistrationI(
    @Json(name = "token") val token: String
)


//secondary

data class UserDataShort(
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "middle_name") val middleName: String,
    @Json(name = "birthday") val birthday: Long,
    @Json(name = "about") val about: String?,
    @Json(name = "phone_number") val phoneNumber: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "link") val link: String?
)