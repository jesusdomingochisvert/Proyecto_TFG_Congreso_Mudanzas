package com.example.congresotfg.common.entities

data class AjenoEntity(

    var id: Long = 0,
    var asistente: AsistenteEntity,
    var actividades: List<ActividadEntity>

)