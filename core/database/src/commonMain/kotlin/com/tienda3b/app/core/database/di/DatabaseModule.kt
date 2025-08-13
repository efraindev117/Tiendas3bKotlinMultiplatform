package com.tienda3b.app.core.database.di

import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.tienda3b.app.core.database.Tienda3bDatabase
import com.tienda3b.app.core.database.dao.ICatsDao
import com.tienda3b.app.core.database.dao.IFavoriteCatsDao
import com.tienda3b.app.core.database.dao.IRemoteKeysDao
import com.tienda3b.app.core.database.dao.SessionDao
import com.tienda3b.app.core.database.dao.UserDao
import com.tienda3b.app.core.database.getDatabaseBuilder
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val databaseModule = module {
    single { tienda3bDatabase(builder = getDatabaseBuilder()) }
    single<ICatsDao> { get<Tienda3bDatabase>().catsDao() }
    single<IRemoteKeysDao> { get<Tienda3bDatabase>().remoteKeysDao() }
    single<IFavoriteCatsDao> { get<Tienda3bDatabase>().favoriteCatsDao() }
    single<UserDao> { get<Tienda3bDatabase>().authDao() }
    single<SessionDao> { get<Tienda3bDatabase>().session() }
}

fun tienda3bDatabase(builder: RoomDatabase.Builder<Tienda3bDatabase>): Tienda3bDatabase {
    return builder.setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO).build()
}