package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.common.entities.PatrocinadorEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.PartnerService
import com.example.congresotfg.common.retrofit.service.PatrocinadorService

class PatrocinadorMethods {
    suspend fun getPatrocinadores() : List<PatrocinadorEntity>? {
        val service = RetrofitClass().getRetrofit().create(PatrocinadorService::class.java)
        val result = service.getPatrocinadores()
        val patrocinadores = result.body()

        return patrocinadores
    }
}