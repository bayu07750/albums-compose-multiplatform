package com.bayu07750.albumsplaceholderkmp

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.di.NetworkModule
import com.bayu07750.albumsplaceholderkmp.core.di.RepositoryModule
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService
import kotlinx.coroutines.Dispatchers
import java.io.File

actual class AppContainer {

    actual fun provideAppDatabase(): Lazy<AppDatabase> {
        val dbFile = File(System.getProperty("java.io.tmpdir"), "my_room.db")
        return lazy {
            Room.databaseBuilder<AppDatabase>(
                name = dbFile.absolutePath,
            )
                .setDriver(BundledSQLiteDriver())
                .setQueryCoroutineContext(Dispatchers.IO)
                .build()
        }
    }

    actual fun provideJSONPlaceholderService(): Lazy<JSONPlaceholderService> {
        return lazy {
            NetworkModule.getJSONPlaceholderService(
                httpClient = NetworkModule.getHttpClient()
            )
        }
    }

    actual fun provideAlbumRepository(): Lazy<AlbumRepository> {
        return lazy {
            RepositoryModule.getAlbumRepository(
                appDatabase = provideAppDatabase().value,
                jsonPlaceholderService = provideJSONPlaceholderService().value
            )
        }
    }
}