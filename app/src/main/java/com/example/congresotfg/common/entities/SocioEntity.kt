package com.example.congresotfg.common.entities

data class SocioEntity(

    var id: Long = 0,
    var cargo:String = "",
    var asistente : AsistenteEntity,
    var empresa : EmpresaEntity

)

