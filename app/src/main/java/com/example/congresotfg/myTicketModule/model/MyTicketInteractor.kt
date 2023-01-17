package com.example.congresotfg.myTicketModule.model

import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MyTicketInteractor {

    fun getAsistente(callback: (AsistenteEntity) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_ASISTENTE_PATH

        var asistente = AsistenteEntity()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

            val status = response.optInt(Constants.STATUS_PROPERTY, Constants.ERROR)

            if (status == Constants.SUCCESS) {

                val jsonList = response.optJSONArray(Constants.CONGRESO_PROPERTY)?.toString()

                if (jsonList != null) {

                    val entityType = object : TypeToken<AsistenteEntity>() {}.type

                    asistente = Gson().fromJson(jsonList, entityType)

                    callback(asistente)

                    return@JsonObjectRequest

                }

            }

            callback(asistente)

        }, {

            it.printStackTrace()

            callback(asistente)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonObjectRequest)

    }

}