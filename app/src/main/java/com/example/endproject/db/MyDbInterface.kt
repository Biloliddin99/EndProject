package com.example.endproject.db

import com.example.endproject.models.User

interface MyDbInterface {

    fun addUser(user: User)
    fun getAllUsers(): ArrayList<User>

}