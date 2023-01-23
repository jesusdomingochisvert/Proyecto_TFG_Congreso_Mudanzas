package com.example.congresotfg.homeModule.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.PatrocinadorEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay

class HomeInteractor {

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

    fun getPatrocinadores(callback: (MutableList<PatrocinadorEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_PATROCINADORES_PATH

        var patrocinadores = mutableListOf<PatrocinadorEntity>()

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            val typeToken = object : TypeToken<MutableList<PatrocinadorEntity>>() {}.type

            patrocinadores = Gson().fromJson(response.toString(), typeToken)

            callback(patrocinadores)

            return@JsonArrayRequest

        }, {

            it.printStackTrace()

            callback(patrocinadores)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonArrayRequest)

    }


}