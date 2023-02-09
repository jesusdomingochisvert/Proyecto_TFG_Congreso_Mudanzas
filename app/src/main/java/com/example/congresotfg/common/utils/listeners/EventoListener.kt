package com.example.congresotfg.common.utils.listeners

import com.example.congresotfg.common.entities.EventoEntity

interface EventoListener {
    fun onClickEvento(eventoEntity: EventoEntity)
}