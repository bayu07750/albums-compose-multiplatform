package com.bayu07750.albumsplaceholderkmp.core.network

import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import kotlinx.coroutines.flow.Flow

interface JSONPlaceholderService {

    fun fetchAlbums(): Flow<Result<List<Album>>>

    fun fetchPhotos(albumId: Int): Flow<Result<List<Photo>>>
}