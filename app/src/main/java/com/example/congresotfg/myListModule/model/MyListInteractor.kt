package com.example.congresotfg.myListModule.model

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyListInteractor {

    fun getEventos(callback: (MutableList<EventoEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_EVENTOS_PATH

        var eventos = mutableListOf<EventoEntity>()

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            val typeToken = object : TypeToken<MutableList<EventoEntity>>() {}.type

            eventos = Gson().fromJson(response.toString(), typeToken)

            callback(eventos)

            return@JsonArrayRequest
        }, {

            it.printStackTrace()

            callback(eventos)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonArrayRequest)

    }

    fun getRestaurantes(callback: (MutableList<RestauranteEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_RESTAURANTES_PATH

        var restaurantes = mutableListOf<RestauranteEntity>()

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            val typeToken = object : TypeToken<MutableList<RestauranteEntity>>() {}.type

            restaurantes = Gson().fromJson(response.toString(), typeToken)

            callback(restaurantes)

            return@JsonArrayRequest

        }, {

            it.printStackTrace()

            callback(restaurantes)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonArrayRequest)

    }

}