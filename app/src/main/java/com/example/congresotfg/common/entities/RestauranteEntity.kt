package com.example.congresotfg.common.entities

data class RestauranteEntity(

    var id: Long = 0,
    var congresoId: Long = 1,
    var nombre: String,
    var tipoComida: String,
    var lugar: String,
    var descripcion: String,
    var imagen: String

): Comparable<RestauranteEntity> {

    override fun compareTo(other: RestauranteEntity): Int {

        return this.nombre.compareTo(other.nombre)

    }


}

