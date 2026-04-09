package com.example.labwork26

import androidx.annotation.StringRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class User(val login: String, var password: String, var email: String)

class UserViewModel : ViewModel() {
    var login by mutableStateOf("")
    var password by mutableStateOf("")
    var email by mutableStateOf("")

    var user by mutableStateOf(User("", "", ""))

    var users = mutableStateListOf(User("user1", "123", "user@mail.com"),
        User("user2", "123", "user123@mial.ru"),
        User("user123", "12345", "UserUser@mail.com"),
        User("User1", "User1", "User1@mail.com"),
        User("1resu", "321", "moc@liam.resu"))

    fun addUser(){
        users.add(User(login, password, email))
    }

    fun getUserByLogin(login: String): User?{
        return users.find{it.login == login}
    }

    fun updateUser(login: String, password: String, email: String){
        val index = users.indexOfFirst { it.login == login }
        if(index != -1){
            users[index] = User(login, password, email)
        }
    }
}