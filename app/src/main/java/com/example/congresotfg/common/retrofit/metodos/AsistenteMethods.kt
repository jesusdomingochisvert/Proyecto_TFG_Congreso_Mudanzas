package com.example.congresotfg.common.retrofit.metodos

import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.AsistenteService

class AsistenteMethods {

    suspend fun putAsistente(username: String? = null, password: String? = null, email: String? = null) {

        val service = RetrofitClass().getRetrofit().create(AsistenteService::class.java)

        service.putAsistente(CongresoApplication.asistente.id, username!!, password!!, email!!)

    }

}