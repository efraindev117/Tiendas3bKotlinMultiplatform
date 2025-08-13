package com.tienda3b.app.core.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import org.koin.mp.KoinPlatform

actual fun getDatabaseBuilder(): RoomDatabase.Builder<Tienda3bDatabase> {
    val context = KoinPlatform.getKoin().get<Application>()
    return Room.databaseBuilder(
        context = context,
        klass = Tienda3bDatabase::class.java,
        name = "cats.db"
    )
        .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
        .enableMultiInstanceInvalidation()
        .fallbackToDestructiveMigration()
}

actual object Tienda3bDatabaseConstructor : RoomDatabaseConstructor<Tienda3bDatabase> {
    override fun initialize(): Tienda3bDatabase = getDatabaseBuilder().build()
}