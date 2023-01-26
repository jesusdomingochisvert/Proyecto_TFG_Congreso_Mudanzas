package com.example.congresotfg.homeModule.model

import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface EventosService {

    @GET(value = Constants.GET_EVENTOS_PATH)
    suspend fun getEventos(): Response<List<EventoEntity>>

}