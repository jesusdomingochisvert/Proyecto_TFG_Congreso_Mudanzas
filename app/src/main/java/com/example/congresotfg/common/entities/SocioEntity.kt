package com.example.congresotfg.common.entities

data class SocioEntity(
    var id: Long = 0,
    var cargo:String,
    var asistente : AsistenteEntity,
    var empresa : EmpresaEntity,

){
    constructor() : this(cargo = "", asistente = AsistenteEntity(), empresa = EmpresaEntity())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SocioEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }


}


