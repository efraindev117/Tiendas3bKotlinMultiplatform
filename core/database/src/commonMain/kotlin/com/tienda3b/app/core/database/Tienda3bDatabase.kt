package com.tienda3b.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.tienda3b.app.core.database.dao.ICatsDao
import com.tienda3b.app.core.database.dao.IFavoriteCatsDao
import com.tienda3b.app.core.database.dao.IRemoteKeysDao
import com.tienda3b.app.core.database.dao.SessionDao
import com.tienda3b.app.core.database.dao.UserDao
import com.tienda3b.app.core.database.model.RemoteKeysEntity
import com.tienda3b.app.core.database.model.auth.SessionEntity
import com.tienda3b.app.core.database.model.auth.UserEntity
import com.tienda3b.app.core.database.model.cats.CatsEntity
import com.tienda3b.app.core.database.model.favorite.FavoriteCatEntity

expect object Tienda3bDatabaseConstructor : RoomDatabaseConstructor<Tienda3bDatabase>

expect fun getDatabaseBuilder(): RoomDatabase.Builder<Tienda3bDatabase>

@Database(
    entities = [
        CatsEntity::class,
        FavoriteCatEntity::class,
        RemoteKeysEntity::class,
        UserEntity::class,
        SessionEntity::class
    ],
    version = 8,
    exportSchema = true
)
abstract class Tienda3bDatabase : RoomDatabase() {
    abstract fun catsDao(): ICatsDao
    abstract fun favoriteCatsDao(): IFavoriteCatsDao
    abstract fun remoteKeysDao(): IRemoteKeysDao
    abstract fun authDao(): UserDao
    abstract fun session(): SessionDao
}