package com.example.congresotfg.common.retrofit.metodos

import android.util.Log
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.PonenteEntity
import com.example.congresotfg.common.entities.ValoracionEntity
import com.example.congresotfg.common.retrofit.RetrofitClass
import com.example.congresotfg.common.retrofit.service.EventoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.math.RoundingMode
import java.text.DecimalFormat

class EventoMethods {


    //EVENTOINFOACTIVITY
    suspend fun getEvento(id:Long?) : EventoEntity{
        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)
        val result = service.getEvento(id)
        val eventoEntity = result.body()!!
        return eventoEntity
    }

    suspend fun getPonentes(id:Long?) : List<PonenteEntity>{
        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)
        val result = service.getPonentes(id)
        val ponentes = result.body()

        return ponentes!!
    }

    suspend fun getAsistentes(id:Long?) : List<AsistenteEntity>{
        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)
        val result = service.getAsistentes(id)
        val asistentes = result.body()

        return asistentes!!
    }

    suspend fun getValoracionMedia(id:Long?) : String? {
        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)
        val result = service.getValoracion(id)
        val v = result.body()
        val double = v?.toDouble()
        val df = DecimalFormat("#.#")
        df.roundingMode = RoundingMode.CEILING
        val valoracion = df.format(double)
        return valoracion
    }

    //----------------------------------------------------------------------------------------------

    //HOMEFRAGMENT

    suspend fun getEventos() : MutableList<EventoEntity>? {
        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)
        val result = service.getEventos()
        val eventos = result.body()

        return eventos
    }

    suspend fun getValoracion() : ValoracionEntity? {

        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)

        val result = service.getAsistenteValoraEvento(CongresoApplication.asistente.id, CongresoApplication.idEvento!!)

        val valoracion = result.body()

        return valoracion

    }

    suspend fun postValoracion(idEven: Long, valoracion: Float) {

        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)

        try {

            service.postAsistenteValoraEvento(CongresoApplication.asistente.id, idEven, valoracion.toString())

        } catch (e: Exception) {

            Log.i("ERROR_POST", e.message.toString())

        }

    }

    suspend fun getAsistenteEventos() : MutableList<EventoEntity> {

        val service = RetrofitClass().getRetrofit().create(EventoService::class.java)

        val result = service.getAsistenteEventos(CongresoApplication.asistente.id)

        val eventos = result.body()!!

        return eventos

    }

}