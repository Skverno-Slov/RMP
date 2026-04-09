package com.example.labwork27.services

import androidx.compose.runtime.mutableStateListOf

class UserService {
    var users = mutableStateListOf<User>()

    fun addUser(regUser: User): Boolean{
        if (users.any { user -> user.login == regUser.login }){
            return false
        }
        users.add(regUser)
        return true
    }

    fun authUser(authUser: User): Boolean{
        try{
            val user = users.first { user -> user.login == authUser.login }
            return user.password == authUser.password
        }
        catch (ex: NoSuchElementException){
            return false
        }
    }

    fun getUserByLogin(login: String): User?{
        return users.find{it.login == login}
    }

    fun updateUser(user: User?, about: String?){
        val index = users.indexOfFirst { it.login == user?.login }
        if(index != -1){
            users[index] = User(user?.login ?: "", user?.password ?: "", user?.age ?: 12, about)
        }
    }
}

data class User(val login: String, var password: String, var age: Int, var about: String?)