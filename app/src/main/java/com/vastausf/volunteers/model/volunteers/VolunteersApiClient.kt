package com.vastausf.volunteers.model.volunteers

import com.vastausf.volunteers.model.volunteers.data.EventDataSearch
import com.vastausf.volunteers.model.volunteers.data.EventsJoinI
import com.vastausf.volunteers.model.volunteers.data.EventsJoinO
import com.vastausf.volunteers.model.volunteers.data.EventsLikeI
import com.vastausf.volunteers.model.volunteers.data.EventsLikeO
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersI
import com.vastausf.volunteers.model.volunteers.data.FindEventsByParametersO
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginI
import com.vastausf.volunteers.model.volunteers.data.TokenCreateByLoginO
import com.vastausf.volunteers.model.volunteers.data.UserDataShort
import com.vastausf.volunteers.model.volunteers.data.UserProfileI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationI
import com.vastausf.volunteers.model.volunteers.data.UserRegistrationO
import io.reactivex.Single
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
    ): Single<TokenCreateByLoginI> {
        return volunteersApiService.tokenCreateByLogin(contentType,
            TokenCreateByLoginO(login, password))
    }

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
    ): Single<UserRegistrationI> {
        return volunteersApiService
            .userCreate(contentType,
                UserRegistrationO(login, password,
                    UserDataShort(firstName,
                        lastName,
                        middleName,
                        birthday,
                        about,
                        phoneNumber,
                        image,
                        email,
                        link)))
    }

    fun userProfile(): Single<UserProfileI> {
        return volunteersApiService.userProfile(
            contentType,
            volunteersTokenStore.accessToken)
    }

    fun findEventsByParameters(
        offset: Long,
        amount: Long,
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

}