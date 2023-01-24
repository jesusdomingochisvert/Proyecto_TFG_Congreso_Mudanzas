package com.example.congresotfg.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

data class RestauranteEntity(

    var id: Long = 0,
    var congresoId: Long = 1,
    var nombre: String,
    var tipo_comida: String,
    var lugar: String,
    var descripcion: String,
    var imagen: String

): Comparable<RestauranteEntity> {

    constructor() : this(nombre = "", tipo_comida = "", lugar = "", descripcion = "", imagen = "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RestauranteEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun compareTo(other: RestauranteEntity): Int {

        return this.nombre.compareTo(other.nombre)

    }


}

