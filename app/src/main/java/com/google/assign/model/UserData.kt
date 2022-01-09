package com.google.assign.model

class UserData: ArrayList<User>()

data class User(
    val address: Address = Address(),
    val avatar: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val phone_number: String = ""
)

data class Address(
    val city: String = "",
)