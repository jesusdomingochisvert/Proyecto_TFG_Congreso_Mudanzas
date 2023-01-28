package com.example.congresotfg.myTicketModule.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.myTicketModule.model.EntradaResponse
import com.example.congresotfg.myTicketModule.model.EntradaService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EntradaViewModel: ViewModel() {

    private val retrofit = Retrofit.Builder().baseUrl(Constants.CONGRESO_URL).addConverterFactory(
        GsonConverterFactory.create()).build()

    private val service = retrofit.create(EntradaService::class.java)

    val entradaInfo = MutableLiveData<EntradaResponse>()

    fun getEntrada(id_asistente: Long) {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                val call = service.getEntrada(id_asistente)

                withContext(Dispatchers.Main) {

                    if (call.isSuccessful && call.body() != null) {

                        call.body().let { entrada ->

                            entradaInfo.postValue(entrada)

                        }

                    } else {

                        Log.i("RESPONSE", "Error en la llamada")

                    }

                }

            } catch (e: Exception) {

                Log.i("RESPONSE", "${e.printStackTrace()}")

            }

        }

    }

}