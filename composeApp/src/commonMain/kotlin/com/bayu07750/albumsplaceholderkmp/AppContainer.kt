package com.bayu07750.albumsplaceholderkmp

import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService

expect class AppContainer {

    fun provideAppDatabase(): Lazy<AppDatabase>

    fun provideJSONPlaceholderService(): Lazy<JSONPlaceholderService>

    fun provideAlbumRepository(): Lazy<AlbumRepository>
}