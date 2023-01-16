package com.example.congresotfg.RestauranteDialogModule.viewModel

import androidx.lifecycle.ViewModel
import com.example.congresotfg.restauranteDialogModule.model.RestauranteDialogInteractor

class RestauranteDialogViewModel : ViewModel(){

    private val interactor: RestauranteDialogInteractor

    init {
        interactor = RestauranteDialogInteractor()
    }
}