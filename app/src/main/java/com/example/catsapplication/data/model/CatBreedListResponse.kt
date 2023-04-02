package com.example.catsapplication.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatBreedListResponse(
    var url: String?,
)
