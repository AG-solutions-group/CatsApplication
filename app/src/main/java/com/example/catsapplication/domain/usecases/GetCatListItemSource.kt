package com.example.catsapplication.domain.usecases

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catsapplication.domain.CatRepository
import com.example.catsapplication.domain.Either
import com.example.catsapplication.domain.model.CatBreed

class GetCatListItemSource(
    private val repository: CatRepository,
    private val breedId: String
) : PagingSource<Int, CatBreed>() {

    override fun getRefreshKey(state: PagingState<Int, CatBreed>): Int? {
        return null
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, CatBreed> {
        val nextPage = params.key ?: 1
        val catList = repository.getCatBreedList(
            page = nextPage,
            breedId = breedId
        )
        return when (catList){
            is Either.Right -> {
                val page = catList.b.first
                val itemList = catList.b.second
                PagingSource.LoadResult.Page(
                    data = itemList,
                    prevKey = if (nextPage == 1) null else nextPage - 1,
                    nextKey = if (itemList.isEmpty()) null else page + 1
                )
            }
            is Either.Left -> {
                Log.e("ItemSource", catList.a.toString())
                PagingSource.LoadResult.Error(Exception(catList.isLeft.toString()))
            }
        }
    }
}