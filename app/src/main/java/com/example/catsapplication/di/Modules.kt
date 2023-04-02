package com.example.catsapplication.di

import com.example.catsapplication.data.CatApi
import com.example.catsapplication.data.CatRepositoryImpl
import com.example.catsapplication.data.retrofitInstanceCatApi
import com.example.catsapplication.domain.CatRepository
import com.example.catsapplication.domain.usecases.GetCatBreedsUsecase
import com.example.catsapplication.presentation.screens.details.DetailsVM
import com.example.catsapplication.presentation.screens.search.SearchVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { DetailsVM(get(), get()) }
    viewModel { SearchVM(get()) }
}

val domainModule = module {
    single {
        GetCatBreedsUsecase(repository = get())
    }
}

val repositoryModule = module {
    fun provideCatApi(catApi: CatApi = retrofitInstanceCatApi()) : CatRepository {
        return CatRepositoryImpl(catApi)
    }

    single { provideCatApi()}
}