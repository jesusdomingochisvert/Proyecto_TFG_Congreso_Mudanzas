package com.example.congresotfg.myListModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.homeModule.model.HomeInteractor
import com.example.congresotfg.myListModule.model.MyListInteractor

class MyListViewModel: ViewModel() {

    private var eventosList: MutableList<EventoEntity>
    private var restaurantesList: MutableList<RestauranteEntity>

    private var interactor: MyListInteractor

    init {

        eventosList = mutableListOf()
        restaurantesList = mutableListOf()

        interactor = MyListInteractor()

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