package com.example.congresotfg.common.retrofit.service

import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST(value=Constants.POST_LOGIN_PATH)
    suspend fun loginAsistente(@Field("username") username: String, @Field("password") password: String): AsistenteEntity

    @GET(value= Constants.GET_ASISTENTE_PATH)
    suspend fun getAsistente(@Path(value = "id") id: Long?): Response<AsistenteEntity>
    @GET(value= Constants.GET_SOCIO)
    suspend fun comprobarSocio(@Path(value = "id_asistente") id: Long): Response<SocioEntity>

}

