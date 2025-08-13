package com.tienda3b.app.core.domain.di

import com.tienda3b.app.core.data.repository.IAuthRepository
import com.tienda3b.app.core.data.repository.IFavorites
import com.tienda3b.app.core.domain.usescase.cats.CatsUsesCase
import com.tienda3b.app.core.domain.usescase.cats.GetCatByIdUseCase
import com.tienda3b.app.core.domain.usescase.cats.GetCatsFlowUseCase
import com.tienda3b.app.core.domain.usescase.cats.LoadNextCatsUseCase
import com.tienda3b.app.core.domain.usescase.cats.RefreshCatsUseCase
import com.tienda3b.app.core.domain.usescase.auth.AuthUsesCase
import com.tienda3b.app.core.domain.usescase.auth.DeleteAllUsesCase
import com.tienda3b.app.core.domain.usescase.auth.IsLoggedInUseCase
import com.tienda3b.app.core.domain.usescase.auth.LoginUseCase
import com.tienda3b.app.core.domain.usescase.auth.LogoutUseCase
import com.tienda3b.app.core.domain.usescase.auth.ObserveAuthStateUseCase
import com.tienda3b.app.core.domain.usescase.auth.ResetPasswordUseCase
import com.tienda3b.app.core.domain.usescase.auth.SignUpUseCase
import com.tienda3b.app.core.domain.usescase.favorites.AddFavoriteUseCase
import com.tienda3b.app.core.domain.usescase.favorites.FavoritesUseCases
import com.tienda3b.app.core.domain.usescase.favorites.ObserveFavoriteIdsUseCase
import com.tienda3b.app.core.domain.usescase.favorites.ObserveFavoritesUseCase
import com.tienda3b.app.core.domain.usescase.favorites.RemoveFavoriteUseCase
import com.tienda3b.app.core.domain.usescase.favorites.ToggleFavoriteUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCatsFlowUseCase(get()) }
    factory { LoadNextCatsUseCase(get()) }
    factory { RefreshCatsUseCase(get()) }
    factory { GetCatByIdUseCase(get()) }
    factory {
        CatsUsesCase(
            get(),
            get(),
            get(),
            get()
        )
    }

    //AuthUsesCase
    single { SignUpUseCase(get()) }
    single { LoginUseCase(get()) }
    single { ResetPasswordUseCase(get()) }
    single { LogoutUseCase(get()) }
    single { DeleteAllUsesCase(get()) }
    single { ObserveAuthStateUseCase(get()) }
    single { IsLoggedInUseCase(get()) }
    factory {
        AuthUsesCase(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    //Favorites
    factory { AddFavoriteUseCase(get()) }
    factory { RemoveFavoriteUseCase(get()) }
    factory { ObserveFavoriteIdsUseCase(get()) }
    single { ToggleFavoriteUseCase(get<IFavorites>()) }
    single { ObserveFavoritesUseCase(get<IFavorites>()) }
    factory {
        FavoritesUseCases(
            add = get(),
            remove = get(),
            observe = get(),
            get(),
            get()
        )
    }
}
