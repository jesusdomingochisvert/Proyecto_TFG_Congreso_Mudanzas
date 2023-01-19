package com.example.congresotfg.myTicketModule.model

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class MyTicketInteractor {

    fun getAsistente(id: Long? = null, callback: (AsistenteEntity) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_ASISTENTE_PATH.replace("{id}", id.toString())

        var asistente = AsistenteEntity()

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            val typeToken = object : TypeToken<MutableList<EventoEntity>>() {}.type

            asistente = Gson().fromJson(response.toString(), typeToken)

            callback(asistente)

            return@JsonArrayRequest

        }, {

            it.printStackTrace()

            callback(asistente)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonArrayRequest)

    }

}