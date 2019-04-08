package com.vastausf.volunteers.adapter.events

data class EventsRecyclerViewItem(
    val id: Long,
    val title: String,
    val place: String,
    val datetime: Long,
    val duration: Long,
    val description: String,
    val likeCount: Long,
    val liked: Boolean,
    val joinCount: Long,
    val joined: Boolean,
    val link: String,
    val image: String?
)