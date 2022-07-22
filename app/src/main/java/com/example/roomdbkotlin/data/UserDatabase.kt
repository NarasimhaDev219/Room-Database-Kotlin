package com.example.roomdbkotlin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdbkotlin.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object{

        @Volatile
        private var INSTANCE:UserDatabase? = null

        fun getDatabase(context: Context) : UserDatabase{
            val tempUnstance = INSTANCE
            if (tempUnstance != null){
                return tempUnstance
            }

            synchronized(this){
                val db = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java, "user_database"
                ).build()
                INSTANCE = db
                return db
            }
        }
    }
}