package com.bayu07750.albumsplaceholderkmp.core.database.entity

import com.bayu07750.albumsplaceholderkmp.core.model.Album
import com.bayu07750.albumsplaceholderkmp.core.model.Photo

abstract class Mapper<FROM, TO> {
    abstract fun map(from: FROM): TO
    abstract fun back(to: TO): FROM
}


object AlbumMapper : Mapper<AlbumEntity, Album>() {
    override fun map(from: AlbumEntity): Album {
        return Album(from.id, from.userId, from.title)
    }

    override fun back(to: Album): AlbumEntity {
        return AlbumEntity(to.id, to.userId, to.title)
    }
}

object  PhotoMapper : Mapper<PhotoEntity, Photo>() {
    override fun map(from: PhotoEntity): Photo {
        return Photo(from.id, from.albumId, from.title, from.url, from.thumbnailUrl)
    }

    override fun back(to: Photo): PhotoEntity {
        return PhotoEntity(to.id, to.albumId, to.title, to.url, to.thumbnailUrl)
    }
}