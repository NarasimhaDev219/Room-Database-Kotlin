package com.example.roomdbkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomdbkotlin.model.User
import com.example.roomdbkotlin.data.UserDatabase
import com.example.roomdbkotlin.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData()
    }

    fun addUser(user: User){
        //this fun running main thread
        viewModelScope.launch (Dispatchers.IO){
            repository.addUserData(user)
        }
    }

    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUserData(user)
        }
    }

    fun deleteUser(user: User){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsersFromDB(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }
}