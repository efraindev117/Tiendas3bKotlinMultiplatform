package com.tienda3b.app.core.network.di

import Tiendas3b.core.network.BuildConfig
import com.tienda3b.app.core.network.api.KtorApiConfig
import com.tienda3b.app.core.network.repository.INetworkCatsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val ktorEngineConfigurationModule = module {
    single {
        KtorClientFactory.create()
    }
    single<INetworkCatsDataSource> {
        KtorApiConfig(httpClient = get())
    }
}

object KtorClientFactory {
    fun create() = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                },
                contentType = ContentType.Application.Json
            )
        }

        install(HttpTimeout) {
            socketTimeoutMillis = 15_000L
            requestTimeoutMillis = 15_000L
            connectTimeoutMillis = 15_000L
        }

        install(DefaultRequest) {
            header("Content-Type", "application/json")
            header("x-api-key", BuildConfig.API_KEY)
        }

        install(Logging) {
            level = LogLevel.ALL
        }
    }
}