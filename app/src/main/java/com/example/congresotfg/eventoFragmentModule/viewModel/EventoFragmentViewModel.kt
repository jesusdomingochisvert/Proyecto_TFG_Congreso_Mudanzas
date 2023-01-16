package com.example.congresotfg.eventoFragmentModule.viewModel

import androidx.lifecycle.ViewModel
import com.example.congresotfg.eventoFragmentModule.model.EventoFragmentInteractor


class EventoFragmentViewModel : ViewModel(){

    private val interactor: EventoFragmentInteractor

    init {
        interactor = EventoFragmentInteractor()
    }
}