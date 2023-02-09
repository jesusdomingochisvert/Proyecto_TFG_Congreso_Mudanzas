package com.example.congresotfg.common.entities

data class AsistenteEntity(

    var id: Long = 0,
    var nombre: String,
    var apellido: String,
    var nombreUsuario : String = "",
    var contrasenya: String = "",
    var codigoPostal : String = "",
    var provincia : String = "",
    var genero : String = "",
    var biografia : String = "",
    var correo : String = "",
    var imagen : String = ""

)
