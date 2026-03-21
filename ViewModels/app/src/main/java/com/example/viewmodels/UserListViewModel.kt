package com.example.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class User(val name: String, val age: Int)

class UserListViewModel: ViewModel() {
    val users = mutableListOf<User>()

    var userName by mutableStateOf("")
    var userAge by mutableStateOf(0)

    fun addUser(){
        users.add(User(userName, userAge))
    }


}