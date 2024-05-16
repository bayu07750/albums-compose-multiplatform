package com.bayu07750.albumsplaceholderkmp.core.di

import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepositoryImp
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService

object RepositoryModule {

    fun getAlbumRepository(
        appDatabase: AppDatabase,
        jsonPlaceholderService: JSONPlaceholderService,
    ) : AlbumRepository {
        return AlbumRepositoryImp(
            service = jsonPlaceholderService,
            appDatabase = appDatabase,
        )
    }
}
