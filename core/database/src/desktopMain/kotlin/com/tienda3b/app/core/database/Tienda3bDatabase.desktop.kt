package com.tienda3b.app.core.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

actual fun getDatabaseBuilder(): RoomDatabase.Builder<Tienda3bDatabase> {
    val dbPath = Paths.get(System.getProperty("user.home"), ".tienda3b", "cats.db")
    Files.createDirectories(dbPath.parent)
    return Room.databaseBuilder<Tienda3bDatabase>(name = dbPath.toString())
        .setDriver(BundledSQLiteDriver())
        .fallbackToDestructiveMigration(true)
}

actual object Tienda3bDatabaseConstructor : RoomDatabaseConstructor<Tienda3bDatabase> {
    override fun initialize(): Tienda3bDatabase = getDatabaseBuilder().build()
}