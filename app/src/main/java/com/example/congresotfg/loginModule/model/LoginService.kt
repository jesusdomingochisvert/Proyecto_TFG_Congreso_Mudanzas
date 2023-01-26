package com.example.congresotfg.LoginModule.model

import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {

    @POST(value=Constants.POST_LOGIN_PATH)
    suspend fun loginAsistente(@Body data: AsistenteInfo): Response<AsistenteEntity>



}

