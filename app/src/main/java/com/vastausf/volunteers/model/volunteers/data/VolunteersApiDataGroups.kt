package com.vastausf.volunteers.model.volunteers.data

import com.squareup.moshi.Json

data class FindGroupsByParametersO(
    @Json(name = "offset") val offset: Int,
    @Json(name = "amount") val amount: Int,
    @Json(name = "parameters") val parameters: GroupDataSearch
)

data class FindGroupsByParametersI(
    @Json(name = "groups") val groups: List<GroupDataFull>
)

/*secondary*/
data class GroupDataFull(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String?,
    @Json(name = "color") val color: String?,
    @Json(name = "image") val image: String?,
    @Json(name = "link") val link: String?,
    @Json(name = "creator_id") val creatorId: Long,
    @Json(name = "joined") val joined: Boolean,
    @Json(name = "administrated") val administrated: Boolean,
    @Json(name = "member_count") val memberCount: Long

)

data class GroupDataSearch(
    @Json(name = "ids") val ids: List<Long>? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "can_post") val canPost: Boolean? = null,
    @Json(name = "link") val link: String? = null,
    @Json(name = "creator_ids") val creatorIds: List<Long>? = null,
    @Json(name = "administrator_ids") val administratorIds: List<Long>? = null
)
