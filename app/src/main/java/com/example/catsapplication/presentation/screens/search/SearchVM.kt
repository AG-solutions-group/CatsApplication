package com.example.catsapplication.presentation.screens.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapplication.domain.Either
import com.example.catsapplication.domain.model.CatBreed
import com.example.catsapplication.domain.usecases.GetCatBreedsUsecase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchVM(
    private val getCatBreedsUseCase: GetCatBreedsUsecase
): ViewModel() {

    private val _catList = MutableStateFlow(emptyList<CatBreed>())
    val catList = _catList.asStateFlow()

    init {
        getCatBreeds()
    }

    private fun getCatBreeds() {

        viewModelScope.launch {
            val result = getCatBreedsUseCase.execute()
            when (result) {
                is Either.Right -> _catList.value = result.b
                is Either.Left -> Log.e("SearchVM", "Error: ${result.a}")
            }
        }
    }
}