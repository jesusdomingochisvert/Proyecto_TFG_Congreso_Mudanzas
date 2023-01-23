package com.example.congresotfg.homeModule.model

import com.google.gson.annotations.SerializedName

data class EventoResponse(@SerializedName("nombre") val nombre: String, @SerializedName("imagen") val imagen: String)