package com.example.congresotfg.common.entities

data class ActividadEntity(

    var id: Long = 0,
    var congresoId: Long = 1,
    var nombre: String,
    var descripcion: String = "",
    var dia: Int,
    var mes: Int,
    var horaInicio: String,
    var horaFin: String = "",
    var imagen: String,

)