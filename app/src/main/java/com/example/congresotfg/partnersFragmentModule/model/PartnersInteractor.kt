package com.example.congresotfg.homeModule.model

import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.utils.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PartnersInteractor {

    fun getSocios(callback: (MutableList<SocioEntity>) -> Unit) {

        val url = Constants.CONGRESO_URL + Constants.GET_ALLPARTNERS_PATH

        var socios = mutableListOf<SocioEntity>()


        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null, { response ->

            val typeToken = object : TypeToken<MutableList<SocioEntity>>() {}.type

            socios = Gson().fromJson(response.toString(), typeToken)

            callback(socios)

            return@JsonArrayRequest
        }, {
            it.printStackTrace()

            callback(socios)
        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonArrayRequest)
    }

}