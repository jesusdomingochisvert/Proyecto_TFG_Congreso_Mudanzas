package com.example.congresotfg.common.entities

data class AsistenteEntity(

    var id: Long = 0,
    var nombre: String,
    var apellidos: String,
    var nombreUsuario : String,
    var contrasenya: String,
    var codigoPostal : String,
    var provincia : String,
    var genero : String,
    var biografia : String,
    var correo : String,
    var imagen : String

) {

    constructor() : this(nombre = "", apellidos = "", nombreUsuario = "", contrasenya = "",
        codigoPostal = "", provincia = "", genero = "", biografia = "", correo = "", imagen = "")

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
