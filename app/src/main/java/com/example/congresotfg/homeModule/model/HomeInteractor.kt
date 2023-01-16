package com.example.congresotfg.homeModule.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeInteractor {

    fun getEventos(callback: (MutableList<EventoEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_EVENTOS_PATH

        var eventos = mutableListOf<EventoEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

            val status = response.optInt(Constants.STATUS_PROPERTY, Constants.ERROR)

            if (status == Constants.SUCCESS) {

                val jsonList = response.optJSONArray(Constants.CONGRESO_PROPERTY)?.toString()

                if (jsonList != null) {

                    val mutableListType = object : TypeToken<MutableList<EventoEntity>>() {}.type

                    eventos = Gson().fromJson(jsonList, mutableListType)

                    callback(eventos)

                    return@JsonObjectRequest

                }

            }

            callback(eventos)

        }, {

            it.printStackTrace()

            callback(eventos)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonObjectRequest)

    }

    fun getRestaurantes(callback: (MutableList<RestauranteEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_RESTAURANTES_PATH

        var restaurantes = mutableListOf<RestauranteEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

            val status = response.optInt(Constants.STATUS_PROPERTY, Constants.ERROR)

            if (status == Constants.SUCCESS) {

                val jsonList = response.optJSONArray(Constants.CONGRESO_PROPERTY)?.toString()

                if (jsonList != null) {

                    val mutableListType = object : TypeToken<MutableList<RestauranteEntity>>() {}.type

                    restaurantes = Gson().fromJson(jsonList, mutableListType)

                    callback(restaurantes)

                    return@JsonObjectRequest

                }

            }

            callback(restaurantes)

        }, {

            it.printStackTrace()

            callback(restaurantes)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonObjectRequest)

    }

}