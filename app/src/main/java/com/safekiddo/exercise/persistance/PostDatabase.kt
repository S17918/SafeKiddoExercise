package com.safekiddo.exercise.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.safekiddo.exercise.domain.model.Post

@Database(entities = [Post::class], version = 1)
//@TypeConverters(Converters::class)
abstract class PostDatabase : RoomDatabase(){

    abstract fun roomPostDao(): PostDao

    companion object{
        private const val DATABASE = "posts_db"

        @Volatile
        private var instance: PostDatabase? = null

        fun getInstance(context: Context): PostDatabase{
            return instance ?: synchronized(this){
                instance ?: postDatabase(context).also { instance = it }
            }
        }

        private fun postDatabase(context: Context): PostDatabase {
            return Room.databaseBuilder(context, PostDatabase::class.java, DATABASE).build()
        }
    }
}