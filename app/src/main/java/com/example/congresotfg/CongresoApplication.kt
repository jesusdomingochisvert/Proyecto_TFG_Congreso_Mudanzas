package com.example.congresotfg

import android.app.Application
import com.example.congresotfg.common.database.CongresoAPI
import com.example.congresotfg.common.entities.AjenoEntity
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.SocioEntity

class CongresoApplication : Application() {

    companion object {

        // Declaramos la variable de la API como variable global en todo el proyecto.

        lateinit var congresoAPI: CongresoAPI

        lateinit var asistente : AsistenteEntity

        lateinit var socio : SocioEntity

        lateinit var ajeno : AjenoEntity

    }

    override fun onCreate() {

        super.onCreate()

        // Iniciamos API

        congresoAPI = CongresoAPI(this)

    }

}