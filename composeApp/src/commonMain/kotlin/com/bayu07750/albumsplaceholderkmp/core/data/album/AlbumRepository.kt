package com.bayu07750.albumsplaceholderkmp.core.data.album

import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    fun getAlbums(): Flow<Result<List<Album>>>

    fun getPhotos(albumId: Int): Flow<Result<List<Photo>>>
}