package com.vastausf.volunteers.model.volunteers.data

import com.squareup.moshi.Json

data class TokenCreateByLoginO(
    @Json(name = "login") val login: String,
    @Json(name = "password") val password: String
)

data class TokenCreateByLoginI(
    @Json(name = "token") val token: String
)

data class TokenRefreshO(
    @Json(name = "refresh_token") val refreshToken: String
)

data class TokenRefreshI(
    @Json(name = "token") val token: String
)
