package com.vastausf.volunteers.model.volunteers.data

import com.squareup.moshi.Json

data class UploadImageI(
    @Json(name = "image_link") val imageLink: String
)