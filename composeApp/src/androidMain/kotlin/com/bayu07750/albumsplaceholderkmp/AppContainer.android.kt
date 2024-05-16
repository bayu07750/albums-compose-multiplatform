package com.bayu07750.albumsplaceholderkmp

import android.app.Application
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.di.NetworkModule
import com.bayu07750.albumsplaceholderkmp.core.di.RepositoryModule
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService
import com.bayu07750.albumsplaceholderkmp.core.util.DB_NAME
import kotlinx.coroutines.Dispatchers

actual class AppContainer(
    private val application: Application,
) {

    actual fun provideAppDatabase(): Lazy<AppDatabase> {
        val dbFile = application.getDatabasePath(DB_NAME)
        return lazy {
            Room.databaseBuilder<AppDatabase>(
                context = application,
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