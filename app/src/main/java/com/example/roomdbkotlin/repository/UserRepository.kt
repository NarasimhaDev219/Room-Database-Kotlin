package com.example.roomdbkotlin.repository

import androidx.lifecycle.LiveData
import com.example.roomdbkotlin.data.UserDao
import com.example.roomdbkotlin.model.User

class UserRepository(private val userDao: UserDao) {


    fun readAllData() : LiveData<List<User>> = userDao.readData()

    suspend fun addUserData(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUserData(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsersList()
    }
}