package com.example.congresotfg.common.entities

import androidx.room.PrimaryKey

data class EmpresaEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nombre:String,
    var enlace:String,
    var direccion:String,
    var logo:String,
    var cif:String

){
    constructor() : this(nombre="", enlace = "", direccion = "", logo = "", cif = "")
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EmpresaEntity

        if (id != other.id) return false
        if (nombre != other.nombre) return false
        if (enlace != other.enlace) return false
        if (direccion != other.direccion) return false
        if (logo != other.logo) return false
        if (cif != other.cif) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nombre.hashCode()
        result = 31 * result + enlace.hashCode()
        result = 31 * result + direccion.hashCode()
        result = 31 * result + logo.hashCode()
        result = 31 * result + cif.hashCode()
        return result
    }
}
