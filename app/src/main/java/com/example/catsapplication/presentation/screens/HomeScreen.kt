package com.example.catsapplication.presentation.screens

import androidx.compose.runtime.Composable
import com.example.catsapplication.presentation.screens.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    // delegation screen at this point due to root nav graph
    navigator.navigate(SearchScreenDestination())

}