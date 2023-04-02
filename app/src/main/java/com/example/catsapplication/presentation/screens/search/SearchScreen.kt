package com.example.catsapplication.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.catsapplication.domain.model.CatBreed
import com.example.catsapplication.presentation.screens.destinations.DetailsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SearchScreen(
    viewModel: SearchVM = koinViewModel<SearchVM>(),
    navigator: DestinationsNavigator
) {
    val catsList = viewModel.catList.collectAsState()

    SearchScreenImpl(navigator, catsList.value)
}

@Composable
fun SearchScreenImpl(
    navigator: DestinationsNavigator,
    catsList: List<CatBreed>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize(),
        columns = GridCells.Fixed(3),
        content = {
            items(catsList) { cat ->
                Card(
                    modifier = Modifier
                        .padding(2.sdp)
                        .aspectRatio(1f, false)
                        .clickable { navigator.navigate(DetailsScreenDestination(cat)) }
                ) {
                    Box {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(cat.imageUrl)
                                .size(Size.ORIGINAL)
                                .crossfade(true)
                                .build(),
                            placeholder = ColorPainter(Color.LightGray),
                            error = ColorPainter(Color.LightGray),
                            contentDescription = "Image",
                            contentScale = ContentScale.Crop
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.LightGray)
                                .align(Alignment.BottomCenter),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = cat.name,
                                maxLines = 1,
                                fontSize = 8.ssp,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    }
                }
            }
        }
    )
}