package com.example.congresotfg.common.entities

data class EntradaEntity(

    val id: Long = 0,
    val congreso_id: Long = 1,
    val qr: String = "",
    val fecha_compra: String,
    val tipo: String,
    var asistente: AsistenteEntity,

) {

    constructor(): this(fecha_compra = "", tipo = "", asistente = AsistenteEntity())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EntradaEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}
