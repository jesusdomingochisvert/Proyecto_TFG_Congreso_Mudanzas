package com.example.congresotfg.common.retrofit.service

import com.example.congresotfg.common.entities.EntradaEntity
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.common.retrofit.model.EntradaResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EntradaService {

    @GET(value = Constants.GET_ENTRADA_PATH)
    suspend fun getEntrada(

        @Path(value = "id_asistente") id_asistente: Long

    ): Response<EntradaEntity>

}