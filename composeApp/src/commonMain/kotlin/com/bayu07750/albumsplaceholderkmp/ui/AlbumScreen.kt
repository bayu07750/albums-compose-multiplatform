package com.bayu07750.albumsplaceholderkmp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.bayu07750.albumsplaceholderkmp.core.model.Album

@Composable
fun AlbumScreen(
    albums: List<Album>,
    onSelectedAlbum: (Album) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
    ) {
        itemsIndexed(
            items = albums,
            key = { index, album -> album.id ?: index },
        ) { index, album ->
            val firstIndex = index == 0
            val lastIndex = index == albums.lastIndex

            val shape = when {
                firstIndex -> RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)
                lastIndex -> RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                else -> RoundedCornerShape(0.dp)
            }

            Surface(
                modifier = Modifier
                    .clickable(
                        onClick = {
                            onSelectedAlbum(album)
                        },
                        role = Role.Button,
                        enabled = true
                    ),
                shape = shape,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 44.dp),
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Text(
                            text = album.title ?: "",
                            modifier = Modifier
                                .weight(1f),
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier,
                        )
                    }
                    if (lastIndex.not()) {
                        Divider(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                        )
                    }
                }
            }
        }
    }
}