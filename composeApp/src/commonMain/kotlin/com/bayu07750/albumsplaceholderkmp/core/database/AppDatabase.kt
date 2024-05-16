package com.bayu07750.albumsplaceholderkmp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bayu07750.albumsplaceholderkmp.core.database.entity.AlbumEntity
import com.bayu07750.albumsplaceholderkmp.core.database.entity.PhotoEntity

@Database(
    entities = [AlbumEntity::class, PhotoEntity::class],
    version = 1,
    exportSchema = true,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao
}