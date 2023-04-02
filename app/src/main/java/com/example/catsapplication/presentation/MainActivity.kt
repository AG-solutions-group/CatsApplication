package com.example.catsapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.catsapplication.presentation.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                CatApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatApp() {

    // should be replaced with current screen title
    val placeholderTitleVar by remember { mutableStateOf("SearchScreen") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = placeholderTitleVar
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors (
                    containerColor = Color.LightGray,
                    titleContentColor = Color.White,
                )
            )
        }
    ) { padding ->

        DestinationsNavHost(
            modifier = Modifier.padding(padding),
            navGraph = NavGraphs.root,
        )
    }
}



