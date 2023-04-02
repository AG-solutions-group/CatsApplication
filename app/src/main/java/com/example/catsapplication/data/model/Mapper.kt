package com.example.catsapplication.data.model

import com.example.catsapplication.domain.ObjectMapper
import com.example.catsapplication.domain.model.CatBreed
import com.example.catsapplication.domain.model.CatBreedAdditionalInfo


class MapperCatBreed : ObjectMapper<CatResponse, CatBreed> {

    override fun map(from: CatResponse): CatBreed {
        return with (from) {
            CatBreed(
                id = id,
                breed = CatBreedAdditionalInfo(
                    breedId = breeds?.get(0)?.breedId ?: "",
                    weight = breeds?.get(0)?.weight?.metric,
                    origin = breeds?.get(0)?.origin,
                    indoor = breeds?.get(0)?.indoor,
                    grooming = breeds?.get(0)?.grooming
                ),
                name = breeds?.get(0)?.name ?: "",
                imageUrl = url,
            )
        }
    }
}

class MapperCatBreedList : ObjectMapper<CatBreedListResponse, CatBreed> {

    override fun map(from: CatBreedListResponse): CatBreed {
        return with (from) {
            CatBreed(
                id = "",
                breed = null,
                name = "",
                imageUrl = url,
            )
        }
    }
}