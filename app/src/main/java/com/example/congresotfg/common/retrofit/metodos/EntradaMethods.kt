package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.common.entities.EntradaEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.model.EntradaResponse
import com.example.congresotfg.common.retrofit.service.EntradaService
import com.example.congresotfg.common.retrofit.service.LoginService

class EntradaMethods {

    suspend fun getEntrada(id:Long) : EntradaEntity {
        val service = RetrofitClass().getRetrofit().create(EntradaService::class.java)
        val result = service.getEntrada(id)
        val entradaEntity = result.body()!!

        return entradaEntity
    }
}