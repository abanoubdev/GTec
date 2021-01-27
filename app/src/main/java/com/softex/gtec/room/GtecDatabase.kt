package com.softex.gtec.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.softex.gtec.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class GtecDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME: String = "gtec_db"
    }
}