package com.example.congresotfg.homeModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.homeModule.model.HomeInteractor

class HomeViewModel: ViewModel() {

    private var eventosList: MutableList<EventoEntity>
    private var restaurantesList: MutableList<RestauranteEntity>

    private var interactor: HomeInteractor

    init {

        eventosList = mutableListOf()
        restaurantesList = mutableListOf()

        interactor = HomeInteractor()

    }

    private val eventos: MutableLiveData<MutableList<EventoEntity>> by lazy {

        MutableLiveData<MutableList<EventoEntity>>().also {

            loadEventos()

        }

    }

    private val restaurantes: MutableLiveData<MutableList<RestauranteEntity>> by lazy {

        MutableLiveData<MutableList<RestauranteEntity>>().also {

            loadRestaurantes()

        }

    }

    fun getEventos(): LiveData<MutableList<EventoEntity>> {

        Log.i("Api", eventos.toString())

        return eventos

    }

    fun getRestaurantes(): LiveData<MutableList<RestauranteEntity>> {

        return restaurantes

    }

    private fun loadEventos() {

        interactor.getEventos {

            eventos.value = it

            eventosList = it

        }

    }

    private fun loadRestaurantes() {

        interactor.getRestaurantes {

            restaurantes.value = it

            restaurantesList = it

        }

    }

}