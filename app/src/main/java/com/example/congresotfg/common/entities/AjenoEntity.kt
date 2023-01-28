package com.example.congresotfg.common.entities

data class AjenoEntity(

    var id: Long = 0,
    var asistente: AsistenteEntity,
    var actividades: List<ActividadEntity>

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AjenoEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}