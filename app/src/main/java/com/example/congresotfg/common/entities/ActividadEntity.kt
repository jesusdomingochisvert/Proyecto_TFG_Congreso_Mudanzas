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
    var ajenoIdAjeno: AjenoEntity

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ActividadEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}