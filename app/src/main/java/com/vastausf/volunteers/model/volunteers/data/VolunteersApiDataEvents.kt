package com.vastausf.volunteers.model.volunteers.data

import com.squareup.moshi.Json

data class FindEventsByParametersO(
    @Json(name = "offset") val offset: Int,
    @Json(name = "amount") val amount: Int,
    @Json(name = "parameters") val parameters: EventDataSearch
)

data class FindEventsByParametersI(
    @Json(name = "events") val events: List<EventDataFull>
)

data class EventsLikeO(
    @Json(name = "event_id") val eventId: Long,
    @Json(name = "state") val state: Boolean
)

data class EventsLikeI(
    @Json(name = "successful") val successful: Boolean
)

data class EventsJoinO(
    @Json(name = "event_id") val eventId: Long,
    @Json(name = "state") val state: Boolean
)

data class EventsJoinI(
    @Json(name = "successful") val successful: Boolean
)

/*secondary*/
data class EventDataFull(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "creator_id") val creatorId: Long,
    @Json(name = "place") val place: String?,
    @Json(name = "datetime") val datetime: Long?,
    @Json(name = "duration") val duration: Long?,
    @Json(name = "description") val description: String?,
    @Json(name = "link") val link: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "liked") val liked: Boolean,
    @Json(name = "like_count") val likeCount: Long,
    @Json(name = "joined") val joined: Boolean,
    @Json(name = "join_count") val joinCount: Long
)

data class EventDataSearch(
    @Json(name = "ids") val ids: List<Int>? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "creator_ids") val creatorIds: List<Int>? = null,
    @Json(name = "place") val place: String? = null,
    @Json(name = "datetime_min") val datetimeMin: Long? = null,
    @Json(name = "datetime_max") val datetimeMax: Long? = null,
    @Json(name = "duration_min") val durationMin: Long? = null,
    @Json(name = "duration_max") val durationMax: Long? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "link") val link: String? = null
)
