package com.example.congresotfg.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RestauranteEntity")
data class RestauranteEntity(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var congresoId: Long,
    var nombre: String,
    var tipo_comida: String,
    var lugar: String,
    var descripcion: String,
    var imagen: String

) {

    constructor() : this(congresoId = 0, nombre = "", tipo_comida = "", lugar = "", descripcion = "", imagen = "")

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


}

