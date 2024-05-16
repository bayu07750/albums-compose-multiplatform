package com.bayu07750.albumsplaceholderkmp

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.database.AppDatabase
import com.bayu07750.albumsplaceholderkmp.core.database.instantiateImpl
import com.bayu07750.albumsplaceholderkmp.core.di.NetworkModule
import com.bayu07750.albumsplaceholderkmp.core.di.RepositoryModule
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService
import com.bayu07750.albumsplaceholderkmp.core.util.DB_NAME
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask


actual class AppContainer {

    @OptIn(ExperimentalForeignApi::class)
    actual fun provideAppDatabase(): Lazy<AppDatabase> {
        val documentDirectory = NSFileManager.defaultManager().URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        checkNotNull(documentDirectory)
        val dbFile = "${documentDirectory.path}/$DB_NAME"
        return lazy {
            Room.databaseBuilder<AppDatabase>(
                name = dbFile,
                factory = { AppDatabase::class.instantiateImpl() }
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