package com.tienda3b.app.core.network.api

import Tiendas3b.core.network.BuildConfig
import com.tienda3b.app.core.network.model.NetworkCatsItemModel
import com.tienda3b.app.core.network.repository.INetworkCatsDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url

class KtorApiConfig(
    private val httpClient: HttpClient,
    private val baseUrl: String = BuildConfig.CATS_BASE_URL
): INetworkCatsDataSource {

    override suspend fun getCatList(
        limit: Int,
        page: Int
    ): List<NetworkCatsItemModel> {
       return httpClient.get {
           url("$baseUrl/breeds")
           parameter("limit",limit)
           parameter("page",page)
       }.body()
    }
}