package com.example.catsapplication.domain

import com.example.catsapplication.domain.model.CatBreed


interface CatRepository {

    suspend fun getCatBreeds() : Either<Failure, List<CatBreed>>
    suspend fun getCatBreedList(page: Int, breedId: String) : Either<Failure, Pair<Int, List<CatBreed>>>
}