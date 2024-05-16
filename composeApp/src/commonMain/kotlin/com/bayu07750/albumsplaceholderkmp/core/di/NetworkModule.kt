package com.bayu07750.albumsplaceholderkmp.core.di

import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderService
import com.bayu07750.albumsplaceholderkmp.core.network.JSONPlaceholderServiceImp
import com.bayu07750.albumsplaceholderkmp.core.util.BASE_URL
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object NetworkModule {

    fun getHttpClient(): HttpClient {
        return HttpClient {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
        }
    }

    fun getJSONPlaceholderService(httpClient: HttpClient): JSONPlaceholderService {
        return JSONPlaceholderServiceImp(
            client = httpClient,
            baseUrl = BASE_URL
        )
    }
}