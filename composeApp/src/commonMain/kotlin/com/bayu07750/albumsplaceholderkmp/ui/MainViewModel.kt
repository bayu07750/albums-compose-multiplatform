package com.bayu07750.albumsplaceholderkmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bayu07750.albumsplaceholderkmp.core.data.album.AlbumRepository
import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MainUiState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val messageError: String? = null,
    val albums: List<Album> = emptyList(),
    val photos: List<Photo> = emptyList(),
    val selectedAlbum: Album? = null,
)

fun MainUiState.canGoBack() = selectedAlbum != null

class MainViewModel(
    private val albumRepository: AlbumRepository,
) : ViewModel() {

    private var _uiState = MutableStateFlow(MainUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        getAlbums()
    }

    fun getAlbums() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            albumRepository.getAlbums().collectLatest { result ->
                if (result.isFailure) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            messageError = result.exceptionOrNull()?.message
                        )
                    }
                    return@collectLatest
                }
                _uiState.update { it.copy(isLoading = false, albums = result.getOrNull() ?: emptyList()) }
            }
        }
    }

    fun setSelectedAlbum(album: Album?) {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedAlbum = album) }
            album?.id?.let {
                getPhotos(it)
            }
        }
    }

    fun resetSelectedAlbum() {
        viewModelScope.launch {
            _uiState.update { it.copy(selectedAlbum = null, photos = emptyList()) }
        }
    }

    fun getPhotos(albumId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            albumRepository.getPhotos(albumId).collectLatest { result ->
                if (result.isFailure) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            isError = true,
                            messageError = result.exceptionOrNull()?.message
                        )
                    }
                    return@collectLatest
                }
                _uiState.update { it.copy(isLoading = false, photos = result.getOrNull() ?: emptyList()) }
            }
        }
    }
}