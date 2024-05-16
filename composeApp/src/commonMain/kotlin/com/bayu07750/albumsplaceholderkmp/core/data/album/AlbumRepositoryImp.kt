package com.bayu07750.albumsplaceholderkmp.core.data.album

import com.bayu07750.albumsplaceholderkmp.core.database.AppDao
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.database.entity.AlbumMapper
import com.bayu07750.albumsplaceholderkmp.core.database.entity.PhotoMapper
import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow

class AlbumRepositoryImp(
    private val service: JSONPlaceholderService,
    private val appDatabase: AppDatabase,
    private val appDao: AppDao = appDatabase.appDao(),
) : AlbumRepository {

    override fun getAlbums(): Flow<Result<List<Album>>> = flow {
        val localAlbums = appDao.getAlbums().firstOrNull() ?: emptyList()

        if (localAlbums.isNotEmpty()) {
            emit(Result.success(localAlbums.map { AlbumMapper.map(it) }))
            return@flow
        }

        service.fetchAlbums().first()
            .onSuccess { result ->
                appDao.insertAlbums(result.map { AlbumMapper.back(it) })
                emit(Result.success(result))
            }
            .onFailure {
                emit(Result.failure(it))
            }
    }

    override fun getPhotos(albumId: Int): Flow<Result<List<Photo>>> = flow {
        val localPhotos = appDao.getPhotosByAlbumId(albumId).firstOrNull() ?: emptyList()
        if (localPhotos.isNotEmpty()) {
            emit(Result.success(localPhotos.map { PhotoMapper.map(it) }))
            return@flow
        }

        service.fetchPhotos(albumId).first()
            .onSuccess { result ->
                appDao.insertPhotos(result.map { PhotoMapper.back(it) })
                emit(Result.success(result))
            }
            .onFailure {
                emit(Result.failure(it))
            }
    }
}