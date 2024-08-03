package com.google.assign.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status")
    val status: Int,
    @SerializedName("error")
    val error: String,
)
