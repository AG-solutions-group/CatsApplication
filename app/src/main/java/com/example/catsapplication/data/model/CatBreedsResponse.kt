package com.example.catsapplication.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatResponse(
    @Json(name = "id"     ) val id     : String,
    @Json(name = "url"    ) val url    : String?,
    @Json(name = "breeds" ) var breeds : List<CatBreedsResponse>?,
)

@JsonClass(generateAdapter = true)
data class CatBreedsResponse(
    @Json(name = "id") val breedId : String,
    @Json(name = "name") val name : String?,
    @Json(name = "weight") val weight: WeightResponse?,
    @Json(name = "origin") val origin: String?,
    @Json(name = "indoor") val indoor: String?,
    @Json(name = "grooming") val grooming: String?,
    @Json(name = "reference_image_id") val referenceImageId : String?
)

@JsonClass(generateAdapter = true)
data class WeightResponse(
    @Json(name = "imperial" ) val imperial : String? = null,
    @Json(name = "metric"   ) val metric   : String? = null
)