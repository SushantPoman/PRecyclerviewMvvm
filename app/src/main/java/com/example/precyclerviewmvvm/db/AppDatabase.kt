package com.example.pprecyclerviewmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.precyclerviewmvvm.models.Movie

@Database(
    entities = [Movie::class],
    version = 1)
abstract class AppDatabase : RoomDatabase() {


    abstract fun getMoviesDao() : MoviesDao


    companion object{

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext
            , AppDatabase::class.java
            , "MyDb.db")
                .build()


    }


}