package com.example.congresotfg.common.utils

import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity

interface OnClickListener {

    fun onClickEvento(eventoEntity: EventoEntity)

    fun onClickRestaurante(restauranteEntity: RestauranteEntity)

}