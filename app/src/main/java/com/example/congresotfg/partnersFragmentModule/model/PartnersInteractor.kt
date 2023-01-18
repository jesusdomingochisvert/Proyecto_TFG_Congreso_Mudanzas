package com.example.congresotfg.homeModule.model

import com.android.volley.Request
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

        val url = Constants.CONGRESO_URL + Constants.GET_EVENTOS_PATH

        var socios = mutableListOf<SocioEntity>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, { response ->

                val jsonList = response.optJSONArray("socio")?.toString()

                if (jsonList != null) {

                    val mutableListType = object : TypeToken<MutableList<SocioEntity>>() {}.type

                    socios = Gson().fromJson(jsonList, mutableListType)

                    callback(socios)

                    return@JsonObjectRequest
                }

            callback(socios)

        }, {

            it.printStackTrace()

            callback(socios)

        })

        CongresoApplication.congresoAPI.addToRequestQueue(jsonObjectRequest)

    }

}