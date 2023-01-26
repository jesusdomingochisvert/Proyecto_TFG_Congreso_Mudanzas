package com.example.congresotfg.homeModule.model

import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface RestauranteService {

    @GET(value = Constants.GET_RESTAURANTES_PATH)
    suspend fun getRestaurantes(): Response<List<RestauranteEntity>>

}