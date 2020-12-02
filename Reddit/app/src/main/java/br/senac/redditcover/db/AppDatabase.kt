package br.senac.redditcover.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.senac.redditcover.model.RoomPost

@Database(entities = arrayOf(RoomPost::class), version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun postDao(): PostDao




}