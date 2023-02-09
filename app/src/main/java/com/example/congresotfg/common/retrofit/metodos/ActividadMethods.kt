package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.common.entities.ActividadEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.RestauranteService
import com.example.congresotfg.homeModule.model.ActividadService

class ActividadMethods {

    suspend fun getActividades() : List<ActividadEntity>? {
        val service = RetrofitClass().getRetrofit().create(ActividadService::class.java)
        val result = service.getActividades()
        val actividades = result.body()

        return actividades
    }
}