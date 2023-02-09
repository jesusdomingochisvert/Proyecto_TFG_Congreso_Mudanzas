package com.example.congresotfg.common.entities

data class EntradaEntity(

    val id: Long = 0,
    val congreso_id: Long = 1,
    val qr: String = "",
    val fechaCompra: String,
    val tipo: String,
    var asistente: AsistenteEntity,

)
