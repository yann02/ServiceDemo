package com.example.appleservicedemo.entity.control

import com.example.appleservicedemo.entity.User

import java.util.ArrayList

object Repository {

    private var users = mutableListOf<User>()

    val formattedUserNames: List<String?>
        get() {
            return users.map { user ->
                if (user.lastName != null) {
                    if (user.firstName != null) {
                        "${user.firstName} ${user.lastName}"
                    } else {
                        user.lastName
                    }
                } else {
                    user.firstName ?: "Unknown"
                }
            }
        }

    init {

        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")

        users.add(user1)
        users.add(user2)
        users.add(user3)
    }

    fun getUsers(): List<User>? {
        return users
    }
}
