package com.example.congresotfg.common.entities

import com.google.gson.annotations.SerializedName

data class EventoEntity(

    var id: Long = 0,
    var congresoId: Long = 1,
    var nombre: String,
    var descripcion: String = "",
    var lugar: String,
    var horaInicio: String,
    var horaFin: String = "",
    var imagen: String,

    ): Comparable<EventoEntity> {

    constructor() : this(nombre = "", lugar = "", horaInicio = "", imagen = "")

    override fun compareTo(other: EventoEntity): Int {

        return this.horaInicio.compareTo(other.horaInicio)

    }

}
