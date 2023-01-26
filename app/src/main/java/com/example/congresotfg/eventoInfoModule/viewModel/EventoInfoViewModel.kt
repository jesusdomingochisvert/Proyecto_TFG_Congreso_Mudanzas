package com.example.congresotfg.eventoInfoModule.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.eventoInfoModule.model.EventoResponse
import com.example.congresotfg.eventoInfoModule.model.EventoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class EventoInfoViewModel : ViewModel(){

    private val retrofit = Retrofit.Builder().baseUrl(Constants.CONGRESO_URL).addConverterFactory(GsonConverterFactory.create()).build()

    private val service = retrofit.create(EventoService::class.java)

    val eventoInfo = MutableLiveData<EventoResponse>()

    fun getEvento(id: Long) {

        val call = service.getEvento(id)

        call.enqueue(object: Callback<EventoResponse> {

            override fun onResponse(call: Call<EventoResponse>, response: Response<EventoResponse>) {

                response.body()?.let { evento ->

                    eventoInfo.postValue(evento)

                }

            }

            override fun onFailure(call: Call<EventoResponse>, t: Throwable) {

                call.cancel()

            }

        })

    }

}