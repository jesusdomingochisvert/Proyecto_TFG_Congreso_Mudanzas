package com.example.congresotfg.common.entities

data class AsistenteEntity(

    var id: Long = 0,
    var nombre: String,
    var apellidos: String,
    var fechaTicket: String,

) {

    constructor() : this(nombre = "", apellidos = "", fechaTicket = "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AsistenteEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}
