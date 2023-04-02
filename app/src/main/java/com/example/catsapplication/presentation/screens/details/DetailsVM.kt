package com.example.catsapplication.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.catsapplication.domain.CatRepository
import com.example.catsapplication.domain.model.CatBreed
import com.example.catsapplication.domain.usecases.GetCatListItemSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DetailsVM(
    private val repository: CatRepository,
    private val catBreed: CatBreed
) : ViewModel() {

    // somewhat broken, maybe because not enough images are available in each category
    val catList : Flow<PagingData<CatBreed>> = Pager(PagingConfig(pageSize = 30)) {
            GetCatListItemSource(
                repository = repository,
                breedId = catBreed.breed?.breedId ?: ""
            )
        }.flow.cachedIn(viewModelScope).map {

        // API giving duplicate images, so filtering them out is necessary
        val catListUrl = mutableSetOf<String>()
            it.filter { catBreed ->
                if (catListUrl.contains(catBreed.imageUrl)) {
                    false
                } else {
                    catListUrl.add(catBreed.imageUrl ?: "")
                }
            }
    }
}