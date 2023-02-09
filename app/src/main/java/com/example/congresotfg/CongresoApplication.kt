package com.example.congresotfg

import android.app.Application
import com.example.congresotfg.common.entities.*
import java.util.Stack

class CongresoApplication : Application() {

    companion object {

        // Declaramos la variable de la API como variable global en todo el proyecto.

        lateinit var asistente : AsistenteEntity
        lateinit var restaurante : RestauranteEntity
        var idEvento: Long? = 0L
        lateinit var bonos: MutableList<BonoEntity>

        var socio : Boolean = false
        var recientes = Stack<EventoEntity>()
        var eventosLike = mutableListOf<EventoEntity>()
        var restauranteLike = mutableListOf<RestauranteEntity>()

    }

}