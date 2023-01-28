package com.example.congresotfg.LoginModule.model

import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginService {

    @FormUrlEncoded
    @POST(value=Constants.POST_LOGIN_PATH)
    suspend fun loginAsistente(@Field("username") username: String, @Field("password") password: String): AsistenteEntity

}

