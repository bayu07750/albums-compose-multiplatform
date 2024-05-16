package com.bayu07750.albumsplaceholderkmp.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bayu07750.albumsplaceholderkmp.core.database.entity.AlbumEntity
import com.bayu07750.albumsplaceholderkmp.core.database.entity.PhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbums(entities: List<AlbumEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(entities: List<PhotoEntity>)

    @Query("SELECT * FROM albums")
    fun getAlbums(): Flow<List<AlbumEntity>>

    @Query("SELECT * FROM photos WHERE albumId = :albumId")
    fun getPhotosByAlbumId(albumId: Int): Flow<List<PhotoEntity>>
}