package com.tienda3b.app.core.network.repository

import com.tienda3b.app.core.network.model.NetworkCatsItemModel

interface INetworkCatsDataSource {
    suspend fun getCatList(limit: Int, page: Int): List<NetworkCatsItemModel>
}