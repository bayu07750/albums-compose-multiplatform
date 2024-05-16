package com.bayu07750.albumsplaceholderkmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PhotoScreen(
    album: Album,
    photos: List<Photo>,
    onResetSelectedAlbum: () -> Unit,
    showToolbar: Boolean = true,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            if (showToolbar)   {
                TopAppBar(
                    backgroundColor = MaterialTheme.colors.background,
                    title = { },
                    navigationIcon = {
                        IconButton(
                            onClick = onResetSelectedAlbum,
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    elevation = 0.dp,
                )
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        album.title ?: "",
                        style = MaterialTheme.typography.h5
                    )
                }
            }
            items(photos) { photo ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    KamelImage(
                        resource = asyncPainterResource(data = photo.thumbnailUrl ?: ""),
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(12.dp)),
                        contentDescription = photo.title ?: "",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        text = photo.title ?: "",
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp, end = 16.dp),
                    )
                }
            }
        }
    }
}


