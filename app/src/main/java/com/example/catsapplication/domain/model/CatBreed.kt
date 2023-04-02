package com.example.catsapplication.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatBreed(
    val id: String,
    val breed: CatBreedAdditionalInfo?,
    val name: String,
    val imageUrl: String?
) : Parcelable

@Parcelize
data class CatBreedAdditionalInfo(
    val breedId: String,
    val weight: String?,
    val origin: String?,
    val indoor: String?,
    val grooming: String?
) : Parcelable
