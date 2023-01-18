package com.example.congresotfg.homeModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.homeModule.model.HomeInteractor
import com.example.congresotfg.homeModule.model.PartnersInteractor

class PartnersViewModel: ViewModel() {

    private var sociosList: MutableList<SocioEntity>

    private var interactor: PartnersInteractor

    init {

        sociosList = mutableListOf()


        interactor = PartnersInteractor()

    }

    private val socios: MutableLiveData<MutableList<SocioEntity>> by lazy {

        MutableLiveData<MutableList<SocioEntity>>().also {

            loadSocios()

        }

    }


    fun getSocios(): LiveData<MutableList<SocioEntity>> {

        return socios

    }


    private fun loadSocios() {

        interactor.getSocios {

            socios.value = it

            sociosList = it

        }

    }

}