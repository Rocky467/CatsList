package com.google.assign.model

import com.google.gson.annotations.SerializedName

class UserResponse : ArrayList<User>()

data class User(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("address")
    val address: Address = Address(),
    @SerializedName("avatar")
    val avatar: String = "",
    @SerializedName("first_name")
    val firstName: String = "",
    @SerializedName("last_name")
    val lastName: String = "",
    @SerializedName("phone_number")
    val phoneNumber: String = ""
) {
    data class Address(
        @SerializedName("city")
        val city: String = "",
    )
}