package com.bayu07750.albumsplaceholderkmp.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bayu07750.albumsplaceholderkmp.AppContainer
import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.ui.component.BackHandler2
import com.bayu07750.albumsplaceholderkmp.ui.component.LoadingContent

@Composable
fun App(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    MaterialTheme {
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = modifier,
        ) {
            composable(
                route = "main"
            ) {
                MainScreen(appContainer)
            }
        }
    }
}

@Composable
fun MainScreen(
    appContainer: AppContainer,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel {
        MainViewModel(appContainer.provideAlbumRepository().value)
    }
) {
    val uiState by viewModel.uiState.collectAsState()

    MainScreen(
        uiState = uiState,
        onSelectedAlbum = viewModel::setSelectedAlbum,
        onResetSelectedAlbum = viewModel::resetSelectedAlbum,
        modifier = modifier,
    )

    if (uiState.canGoBack()) {
        BackHandler2(true) {
            viewModel.resetSelectedAlbum()
        }
    }
}

@Composable
fun MainScreen(
    uiState: MainUiState,
    onSelectedAlbum: (Album) -> Unit,
    onResetSelectedAlbum: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(modifier = modifier) {
        val mobile = maxWidth < 600.dp
        if (mobile) {
            MobileScreen(
                uiState = uiState,
                onSelectedAlbum = onSelectedAlbum,
                onResetSelectedAlbum = onResetSelectedAlbum,
            )
        } else {
            DesktopScreen(
                uiState = uiState,
                onSelectedAlbum = onSelectedAlbum,
                onResetSelectedAlbum = onResetSelectedAlbum,
            )
        }
    }
}

@Composable
fun DesktopScreen(
    uiState: MainUiState,
    onSelectedAlbum: (Album) -> Unit,
    onResetSelectedAlbum: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        AlbumScreen(
            albums = uiState.albums,
            onSelectedAlbum = onSelectedAlbum,
            modifier = Modifier
                .weight(2f)
        )
        val weightModifier = Modifier.weight(3f)
        if (uiState.selectedAlbum == null) {
            Box(
                modifier = weightModifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select an album",
                    style = MaterialTheme.typography.h5,
                )
            }
        } else {
            LoadingContent(
                isLoading = uiState.isLoading,
                modifier = weightModifier,
            ) {
                PhotoScreen(
                    modifier = weightModifier,
                    album = uiState.selectedAlbum,
                    photos = uiState.photos,
                    onResetSelectedAlbum = onResetSelectedAlbum,
                    showToolbar = false,
                )
            }
        }
    }
}

@Composable
fun MobileScreen(
    uiState: MainUiState,
    onSelectedAlbum: (Album) -> Unit,
    onResetSelectedAlbum: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LoadingContent(
        isLoading = uiState.isLoading,
        modifier = modifier,
    ) {
        if (uiState.selectedAlbum == null) {
            AlbumScreen(
                albums = uiState.albums,
                onSelectedAlbum = onSelectedAlbum,
            )
        } else {
            PhotoScreen(
                album = uiState.selectedAlbum,
                photos = uiState.photos,
                onResetSelectedAlbum = onResetSelectedAlbum
            )
        }
    }
}
