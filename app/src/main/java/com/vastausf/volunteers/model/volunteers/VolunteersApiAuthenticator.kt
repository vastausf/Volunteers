package com.vastausf.volunteers.model.volunteers

import com.squareup.moshi.Moshi
import com.vastausf.volunteers.R
import com.vastausf.volunteers.VolunteersApplication
import com.vastausf.volunteers.model.HttpStatusCodes
import com.vastausf.volunteers.model.volunteers.data.TokenRefreshI
import com.vastausf.volunteers.model.volunteers.data.TokenRefreshO
import okhttp3.Authenticator
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class VolunteersApiAuthenticator
@Inject
constructor(
    private val volunteersApplication: VolunteersApplication,
    private val volunteersTokenStore: VolunteersTokenStore,
    private val okHttpClient: OkHttpClient,
    private val moshi: Moshi
) : Authenticator {

    @Throws
    override fun authenticate(route: Route?, response: Response): Request? {
        val refreshToken = volunteersTokenStore.refreshToken

        val tokenRefreshResponse = try {
            if (refreshToken != null) {
                okHttpClient.newCall(
                    Request
                        .Builder()
                        .post(RequestBody.create(
                            MediaType.get("application/json"),
                            moshi
                                .adapter(TokenRefreshO::class.java)
                                .toJson(TokenRefreshO(
                                    refreshToken
                                ))
                        ))
                        .url("${volunteersApplication.getString(R.string.volunteers_server_base_url)}/auth/token/create/byRefreshToken")
                        .build()
                )
                    .execute()
            } else {
                throw Unauthorized()
            }
        } catch (e: Exception) {
            throw e
        }

        return when (tokenRefreshResponse.code()) {
            HttpStatusCodes.OK -> {
                val tokenRefreshI =
                    moshi
                        .adapter(TokenRefreshI::class.java)
                        .fromJson(tokenRefreshResponse.body()?.string().toString())

                volunteersTokenStore.accessToken = tokenRefreshI?.token
                volunteersTokenStore.refreshToken = refreshToken

                response
                    .request()
                    .newBuilder()
                    .build()
            }
            HttpStatusCodes.UNAUTHORIZED -> {
                throw Unauthorized()
            }
            else -> {
                null
            }
        }
    }

}