package com.example.congresotfg

import android.app.Application
import com.example.congresotfg.common.database.CongresoAPI

class CongresoApplication : Application() {

    companion object {

        // Declaramos la variable de la API como variable global en todo el proyecto.

        lateinit var congresoAPI: CongresoAPI

    }

    override fun onCreate() {

        super.onCreate()

        // Iniciamos API

        congresoAPI = CongresoAPI(this)

    }

}