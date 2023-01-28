package com.example.congresotfg.homeModule.model

import com.example.congresotfg.common.entities.ActividadEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ActividadesService {

    @GET(value = Constants.GET_ACTIVIDADES_PATH)
    suspend fun getActividades(): Response<List<ActividadEntity>>

}