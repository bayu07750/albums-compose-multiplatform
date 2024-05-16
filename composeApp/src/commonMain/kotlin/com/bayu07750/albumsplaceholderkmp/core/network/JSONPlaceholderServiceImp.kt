package com.bayu07750.albumsplaceholderkmp.core.network

import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class JSONPlaceholderServiceImp(
    private val client: HttpClient,
    private val baseUrl: String,
) : JSONPlaceholderService {

    override fun fetchAlbums(): Flow<Result<List<Album>>> = flow {
        val response: List<Album> = client.get(urlString = "$baseUrl/albums").body()
        emit(Result.success(response))
    }.catchIt()

    override fun fetchPhotos(albumId: Int): Flow<Result<List<Photo>>> = flow {
        val response: List<Photo> = client.get(urlString = "$baseUrl/albums/${albumId}/photos").body()
        emit(Result.success(response))
    }.catchIt()

    private inline fun <reified T> Flow<Result<T>>.catchIt(): Flow<Result<T>> {
        return catch {
            if (it is CancellationException) throw it
            emit(Result.failure(it))
        }
    }
}