package com.example.congresotfg.common.entities

data class ValoracionEntity(
    var asistente : AsistenteEntity,
    var evento : EventoEntity,
    var valoracion : String = ""
)
