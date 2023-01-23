package com.example.congresotfg.homeModule.model

import com.example.congresotfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EventoService {

    @GET(value = "/evento")
    suspend fun getEvento(

        @Query(value = "{id}", encoded = true) id: String

    ): Response<EventoResponse>

}