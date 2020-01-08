package com.example.imbdapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imbdapp.models.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDataBase: RoomDatabase() {

    abstract fun monsterDao(): MovieDao

    companion object {
        @Volatile
        private var INSTANCE: MovieDataBase? = null

        fun getDatabase(context: Context): MovieDataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDataBase::class.java,
                        "movies.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}