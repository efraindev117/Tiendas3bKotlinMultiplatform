package com.tienda3b.app.core.data.di

import com.tienda3b.app.core.common.di.AppDispatchers
import com.tienda3b.app.core.data.repository.AuthRepositoryImpl
import com.tienda3b.app.core.data.repository.CatsRepositoryImpl
import com.tienda3b.app.core.data.repository.FavoriteCatRepoImpl
import com.tienda3b.app.core.data.repository.IAuthRepository
import com.tienda3b.app.core.data.repository.ICatsRepository
import com.tienda3b.app.core.data.repository.IFavorites
import com.tienda3b.app.core.data.utils.INetworkMonitor
import com.tienda3b.app.core.data.utils.IPlatformDependentDataModule
import com.tienda3b.app.core.data.utils.getPlatformDataModule
import com.tienda3b.app.core.data.utils.platformModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<ICatsRepository> {
        CatsRepositoryImpl(
            network = get(),
            catsDao = get(),
            keysDao = get()
        )
    }

    single<IFavorites> {
        FavoriteCatRepoImpl(
            dao = get(),
            io = get(named(AppDispatchers.Default.name))
        )
    }

    single<IAuthRepository> {
        AuthRepositoryImpl(
            userDao = get(),
            sessionDao = get()
        )
    }


    includes(platformModule)
    single<IPlatformDependentDataModule> { getPlatformDataModule }
    single<INetworkMonitor> { getPlatformDataModule.networkMonitor }
}