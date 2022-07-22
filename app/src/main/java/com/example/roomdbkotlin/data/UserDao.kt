package com.example.roomdbkotlin.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdbkotlin.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//IGNORE
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser (user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsersList()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readData() : LiveData<List<User>>
}