package com.example.labwork27

import androidx.lifecycle.ViewModel
import com.example.labwork27.services.User
import com.example.labwork27.services.UserService

class UserViewModel() : ViewModel() {
    val service: UserService = UserService()

    fun registerUser(user: User){
        service.addUser(user)
    }

    fun authUser(authUser: User): Boolean{
        return service.authUser(authUser)
    }

    fun getUser(login: String) : User?{
        return service.getUserByLogin(login)
    }

    fun updateUser(user: User?, about: String?){
        service.updateUser(user, about)
    }
}