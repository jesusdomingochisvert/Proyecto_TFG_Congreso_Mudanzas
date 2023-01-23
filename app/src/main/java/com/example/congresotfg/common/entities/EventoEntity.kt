package com.example.congresotfg.common.entities

data class EventoEntity(

    var id: Long = 0,
    var congresoId: Long,
    var nombre: String,
    var descripcion: String,
    var lugar: String,
    var horaInicio: String,
    var horaFin: String,
    var imagen: String

) {

    constructor() : this(congresoId = 0, nombre = "", descripcion = "", lugar = "", horaInicio = "", horaFin = "", imagen = "")

}