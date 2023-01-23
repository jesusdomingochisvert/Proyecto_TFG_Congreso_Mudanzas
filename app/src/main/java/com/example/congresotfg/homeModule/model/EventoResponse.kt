package com.example.congresotfg.homeModule.model

import com.example.congresotfg.common.entities.EventoEntity
import com.google.gson.annotations.SerializedName

data class EventoResponse(@SerializedName("evento") val evento: EventoEntity)