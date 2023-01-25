package com.example.congresotfg.LoginModule.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String

) {
}