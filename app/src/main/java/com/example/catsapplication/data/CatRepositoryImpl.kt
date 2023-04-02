package com.example.catsapplication.data

import android.util.Log
import com.example.catsapplication.data.model.MapperCatBreed
import com.example.catsapplication.data.model.MapperCatBreedList
import com.example.catsapplication.domain.CatRepository
import com.example.catsapplication.domain.Either
import com.example.catsapplication.domain.Failure
import com.example.catsapplication.domain.model.CatBreed
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class CatRepositoryImpl(
    private val catApi: CatApi
) : CatRepository {

    private val getCatBreedsMapper: MapperCatBreed = MapperCatBreed()
    override suspend fun getCatBreeds(): Either<Failure, List<CatBreed>> {
        return saveApiCall {
            withContext(Dispatchers.IO) {

                val result = catApi.getCatBreeds()

                // Fetch cat images concurrently
                val fetchCatImagesJobs = result.map { catbreed ->
                        async { catApi.getCatImages(id = catbreed.referenceImageId ?: "") }
                }

                // Await all jobs to complete and associate images with cat breeds
                fetchCatImagesJobs.awaitAll().map() { cats ->
                    getCatBreedsMapper.map(cats)
                }
            }
        }
    }

    private val getCatBreedListMapper: MapperCatBreedList = MapperCatBreedList()
    override suspend fun getCatBreedList(
        page: Int,
        breedId: String
    ): Either<Failure, Pair<Int, List<CatBreed>>> {
        return saveApiCall {
            withContext(Dispatchers.IO) {

                val result = catApi.getCatBreedList(breed = breedId, page = page)

                Pair(page, result.map { catbreed ->
                    getCatBreedListMapper.map(catbreed)
                })
            }
        }
    }
}