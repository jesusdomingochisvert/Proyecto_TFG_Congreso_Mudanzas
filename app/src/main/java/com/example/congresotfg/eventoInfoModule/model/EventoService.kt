package com.example.congresotfg.eventoInfoModule.model

import com.example.congresotfg.common.utils.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EventoService {

    @GET(value = Constants.GET_EVENTO_PATH)
    fun getEvento(

        @Path(value = "id") id: Long

    ): Call<EventoResponse>

}