package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.EventoService
import com.example.congresotfg.common.retrofit.service.PartnerService

class PartnerMethods {

    suspend fun getPartners() : List<SocioEntity>? {
        val service = RetrofitClass().getRetrofit().create(PartnerService::class.java)
        val result = service.getAllPartners()
        val socios = result.body()

        return socios
    }
}