package com.example.congresotfg.LoginModule.model

import com.example.congresotfg.common.utils.Constants
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @Headers("Content-Type: application/json")

    @POST(Constants.POST_LOGIN_PATH)
    suspend fun login(@Body username: String, @Body password: String): LoginResponse

}