package com.google.assign.model

import java.io.Serializable

class UserData: ArrayList<User>()

data class User(
    val address: Address,
    val avatar: String,
    val first_name: String,
    val last_name: String,
    val phone_number: String
): Serializable

data class Address(
    val city: String,
)