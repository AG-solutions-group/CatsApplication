package com.example.catsapplication.presentation.screens.details

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.catsapplication.domain.model.CatBreed
import com.ramcosta.composedestinations.annotation.Destination
import ir.kaaveh.sdpcompose.sdp
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun DetailsScreen(
    catBreed: CatBreed,
    viewModel: DetailsVM = getViewModel<DetailsVM>(parameters = { parametersOf(catBreed) })
) {
    val catsList = viewModel.catList.collectAsLazyPagingItems()

    DetailsScreenImpl(catsList, catBreed)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailsScreenImpl(
    catsList: LazyPagingItems<CatBreed>,
    catDetails: CatBreed
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        content = {
            stickyHeader {
                Card() {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Name: ${catDetails.name}",
                            )
                            catDetails.breed?.origin?.let {
                                Text(
                                    text = "Origin: $it",
                                )
                            }
                            catDetails.breed?.weight?.let {
                                Text(
                                    text = "Weight: $it kg",
                                )
                            }
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            catDetails.breed?.grooming?.let {
                                Text(
                                    text = "Grooming: $it/5",
                                )
                            }
                            catDetails.breed?.indoor?.let {
                                Text(
                                    text = "Indoor: $it/5",
                                )
                            }
                        }
                    }
                }
            }

            items(catsList) { cat ->
                Log.d("DetailsScreen", "cat: $cat")
                cat?.imageUrl?.let {imageUrl ->
                        Card(
                            modifier = Modifier
                                .padding(horizontal = 10.sdp, vertical = 2.sdp)
                                .aspectRatio(1f, false)
                        ) {
                            Box {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(imageUrl)
                                        .size(Size.ORIGINAL)
                                        .crossfade(true)
                                        .build(),
                                    placeholder = ColorPainter(Color.LightGray),
                                    error = ColorPainter(Color.LightGray),
                                    contentDescription = "Image",
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                }
            }
        }
    )
}