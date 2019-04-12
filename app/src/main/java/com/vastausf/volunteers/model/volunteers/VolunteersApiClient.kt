package com.vastausf.volunteers.model.volunteers

import com.vastausf.volunteers.model.volunteers.data.EventDataSearch
import com.vastausf.volunteers.model.volunteers.data.EventsJoinI
import com.vastausf.volunteers.model.volunteers.data.EventsJoinO
import com.vastausf.volunteers.model.volunteers.data.EventsLikeI
import com.vastausf.volunteers.model.volunteers.data.EventsLikeO
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersO
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindGroupsByParametersO
import com.vastausf.volunteers.model.volunteers.data.GroupDataSearch
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginI
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginO
import com.vastausf.volunteers.model.volunteers.data.UploadImageI
import com.vastausf.volunteers.model.volunteers.data.UserDataShort
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationO
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import java.io.File
import javax.inject.Inject

class VolunteersApiClient
@Inject constructor(
    private val volunteersApiService: VolunteersApiService,
    private val volunteersTokenStore: VolunteersTokenStore
) {

    private val contentType: String =
        "application/json"

    private val contentTypeImage: String =
        "image/*"

    fun createTokenByLogin(
        login: String,
        password: String
    ): Single<TokenCreateByLoginI> {
        return volunteersApiService.tokenCreateByLogin(contentType,
            TokenCreateByLoginO(login, password))
    }

    fun userCreate(
        login: String,
        password: String,
        userDataShort: UserDataShort
    ): Single<UserRegistrationI> {
        return volunteersApiService
            .userCreate(contentType,
                UserRegistrationO(login, password,
                    userDataShort))
    }

    fun userProfile(): Single<UserProfileI> {
        println(volunteersTokenStore.accessToken)
        return volunteersApiService.userProfile(
            contentType,
            volunteersTokenStore.accessToken)
    }

    fun findEventsByParameters(
        offset: Int,
        amount: Int,
        parameters: EventDataSearch
    ): Single<FindEventsByParametersI> {
        return volunteersApiService.eventsFindByParameters(
            contentType,
            volunteersTokenStore.accessToken,
            FindEventsByParametersO(
                offset,
                amount, parameters
            )
        )
    }

    fun likeEvent(
        eventId: Long,
        state: Boolean
    ): Single<EventsLikeI> {
        return volunteersApiService.eventsLike(
            contentType,
            volunteersTokenStore.accessToken,
            EventsLikeO(
                eventId,
                state
            )
        )
    }

    fun joinEvent(
        eventId: Long,
        state: Boolean
    ): Single<EventsJoinI> {
        return volunteersApiService.eventsJoin(
            contentType,
            volunteersTokenStore.accessToken,
            EventsJoinO(
                eventId,
                state
            )
        )
    }

    fun findGroupsByParameters(
        offset: Int,
        amount: Int,
        parameters: GroupDataSearch
    ): Single<FindGroupsByParametersI> {
        return volunteersApiService.groupsFindByParameters(
            contentType,
            volunteersTokenStore.accessToken,
            FindGroupsByParametersO(
                offset,
                amount,
                parameters
            )
        )
    }

    fun uploadImage(
        image: File
    ): Single<UploadImageI> {
        return volunteersApiService.uploadImage(
            contentTypeImage,
            RequestBody.create(MediaType.parse("image/*"), image)
        )
    }

}
