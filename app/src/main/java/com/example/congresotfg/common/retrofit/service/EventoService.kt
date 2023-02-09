package com.example.congresotfg.common.retrofit.service

import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.PonenteEntity
import com.example.congresotfg.common.entities.ValoracionEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.*

interface EventoService {

    @GET(value = Constants.GET_EVENTOS_PATH)
    suspend fun getEventos(): Response<MutableList<EventoEntity>>

    @GET(value= Constants.GET_EVENTO_PATH)
    suspend fun getEvento(@Path(value = "id") id: Long?): Response<EventoEntity>

    @GET(value= Constants.GET_VALORACION_PATH)
    suspend fun getValoracion(@Path(value = "id_evento") id: Long?): Response<String>

    @GET(value= Constants.GET_EVENTO_PONENTES_PATH)
    suspend fun getPonentes(@Path(value = "id_evento") id: Long?):Response<List<PonenteEntity>>

    @GET(value= Constants.GET_EVENTO_ASISTENTES_PATH)
    suspend fun getAsistentes(@Path(value = "id_evento") id: Long?):Response<List<AsistenteEntity>>

    @GET(value= Constants.GET_ASISTENTE_VALORA_EVENTO_PATH)
    suspend fun getAsistenteValoraEvento(

        @Path("id_asistente") idAsist: Long,
        @Path("id_evento") idEven: Long

    ): Response<ValoracionEntity>

    @GET(value= Constants.GET_ASISTENTE_EVENTOS_PATH)
    suspend fun getAsistenteEventos(@Path("id_asistente") idAsist: Long): Response<MutableList<EventoEntity>>

    @FormUrlEncoded
    @POST(value = Constants.POST_ASISTENTE_VALORA_EVENTO_PATH)
    suspend fun postAsistenteValoraEvento(

        @Path("id_asistente") idAsist: Long,
        @Path("id_evento") idEven: Long,

        @Field("valoracion") valoracion: String

    ): Response<ValoracionEntity>

}

