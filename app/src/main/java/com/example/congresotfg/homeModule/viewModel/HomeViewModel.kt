package com.example.congresotfg.homeModule.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.PatrocinadorEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.homeModule.model.EventoResponse
import com.example.congresotfg.homeModule.model.HomeInteractor
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    private var restaurantesList: MutableList<RestauranteEntity>
    private var patrocinadoresList: MutableList<PatrocinadorEntity>

    private var interactor: HomeInteractor

    init {

        restaurantesList = mutableListOf()
        patrocinadoresList = mutableListOf()

        interactor = HomeInteractor()

    }

    private val evento: MutableLiveData<EventoResponse> by lazy {

        MutableLiveData<EventoResponse>().also {



        }

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

    private val patrocinadores: MutableLiveData<MutableList<PatrocinadorEntity>> by lazy {

        MutableLiveData<MutableList<PatrocinadorEntity>>().also {

            loadPatrocinadores()

        }

    }

    fun getEvento(id: String? = null): LiveData<EventoResponse> {

        return evento

    }

    fun getEventos(): LiveData<MutableList<EventoEntity>> {

        return eventos

    }

    fun getPatrocinadores(): LiveData<MutableList<PatrocinadorEntity>> {

        return patrocinadores

    }

    fun getRestaurantes(): LiveData<MutableList<RestauranteEntity>> {

        return restaurantes

    }

    fun loadEvento(id: String) {

        Log.i("IDIDIDID", "OK3")

        interactor.getEvento(id) {

            evento.value = it

            Log.i("IDIDIDID", "OK2")

        }

    }

    private fun loadEventos() {

        interactor.getEventos {

            eventos.value = it


        }

    }

    private fun loadRestaurantes() {

        interactor.getRestaurantes {

            restaurantes.value = it

            restaurantesList = it

        }

    }

    private fun loadPatrocinadores() {
        viewModelScope.launch {
            interactor.getPatrocinadores {

                patrocinadores.value = it

            }
        }

    }

}