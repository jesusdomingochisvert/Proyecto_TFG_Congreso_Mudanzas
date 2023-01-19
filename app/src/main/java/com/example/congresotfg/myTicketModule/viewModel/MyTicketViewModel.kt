package com.example.congresotfg.myTicketModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.homeModule.model.HomeInteractor
import com.example.congresotfg.myTicketModule.model.MyTicketInteractor

class MyTicketViewModel: ViewModel() {

    private var asistente = MutableLiveData<AsistenteEntity>()

    fun getAsistente(): LiveData<AsistenteEntity> {

        return asistente

    }

    fun setAsistente(asistenteEntity: AsistenteEntity) {

        asistente.value = asistenteEntity

    }

}