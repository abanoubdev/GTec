package com.softex.gtec.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softex.gtec.room.BlogCacheEntity
import com.softex.gtec.room.BlogDao

@Database(entities = [BlogCacheEntity::class], version = 1, exportSchema = false)
abstract class BlogDatabase : RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object {
        val DATABASE_NAME: String = "blog_db"
    }

}