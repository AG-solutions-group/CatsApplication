package com.example.catsapplication.domain.usecases

import com.example.catsapplication.domain.CatRepository
import com.example.catsapplication.domain.Either
import com.example.catsapplication.domain.Failure
import com.example.catsapplication.domain.model.CatBreed

class GetCatBreedsUsecase(private val repository: CatRepository) {

    suspend fun execute() : Either<Failure, List<CatBreed>> {
        return repository.getCatBreeds()
    }
}