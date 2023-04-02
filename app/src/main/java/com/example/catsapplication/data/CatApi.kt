package com.example.catsapplication.data

import com.example.catsapplication.data.model.CatBreedListResponse
import com.example.catsapplication.data.model.CatBreedsResponse
import com.example.catsapplication.data.model.CatResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import retrofit2.http.*

interface CatApi {

    @Headers("Content-Type: application/json",
        "x-api-key: live_gBfyLHlx0WyOjeVNOqyiTXCcrXlWWxCc0OkUscYugvNZSIaFkTponA8nxvwJrptf")
    @GET("breeds")
    suspend fun getCatBreeds(
        @Query("limit") limit: Int = 12,
    ): List<CatBreedsResponse>

    @Headers("x-api-key: live_gBfyLHlx0WyOjeVNOqyiTXCcrXlWWxCc0OkUscYugvNZSIaFkTponA8nxvwJrptf")
    @GET("images/{image_id}")
    suspend fun getCatImages(
        @Path("image_id") id: String
    ): CatResponse

    @Headers("Content-Type: application/json",
        "x-api-key: live_gBfyLHlx0WyOjeVNOqyiTXCcrXlWWxCc0OkUscYugvNZSIaFkTponA8nxvwJrptf")
    @GET("images/search")
    suspend fun getCatBreedList(
        @Query("breed_ids") breed: String,
        @Query("page") page: Int,
        @Query("limit") limit: Int = 10,
    ): List<CatBreedListResponse>
}