package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.EventoService
import com.example.congresotfg.common.retrofit.service.RestauranteService

class RestauranteMethods {

    suspend fun getRestaurantes() : MutableList<RestauranteEntity>? {
        val service = RetrofitClass().getRetrofit().create(RestauranteService::class.java)
        val result = service.getRestaurantes()
        val restaurantes = result.body()

        return restaurantes
    }

    //RESTAURANTEINFOACTIVITY
    suspend fun getRestaurante(id:Long?) : RestauranteEntity{
        val service = RetrofitClass().getRetrofit().create(RestauranteService::class.java)
        val result = service.getRestaurante(id)
        val restauranteEntity = result.body()!!

        return restauranteEntity
    }
}