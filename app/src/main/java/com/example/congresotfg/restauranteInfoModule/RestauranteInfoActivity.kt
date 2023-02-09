package com.example.congresotfg.restauranteInfoModule

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.retrofit.metodos.BonoMethods
import com.example.congresotfg.common.retrofit.metodos.RestauranteMethods
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.databinding.ActivityRestauranteDialogBinding
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import java.sql.Time

class RestauranteInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestauranteDialogBinding
    private lateinit var context: Context

    var idBono = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this

        getRestaurante()

        binding.btnReservar.setOnClickListener {

            reservar()

        }

        binding.btnBack.setOnClickListener{
            onBackPressed()
        }
    }
    private fun getRestaurante(){

        val id = intent.extras?.getLong("id_restaurante")

        CorrutinaClass().executeAction(this) {

            CongresoApplication.restaurante = RestauranteMethods().getRestaurante(id)

            val restauranteEntity = CongresoApplication.restaurante

            with(binding){

                nombreRestaurante.text = restauranteEntity.nombre.uppercase()
                lugarTxt.text=restauranteEntity.lugar
                tipocomidaTxt.text=restauranteEntity.tipoComida
                descropcionTxt.text=restauranteEntity.descripcion
                ImageClass().loadImage(restauranteEntity.imagen,imagen,context)
                progressbar.visibility=GONE
                imagen.visibility = VISIBLE
                containerDialogRestaurante.visibility=VISIBLE

            }

        }

    }

    private fun reservar() {

        try {

            CorrutinaClass().executeAction(this) {

                if (comprobar()) {

                    BonoMethods().deleteBonoAsistente(idBono)

                    Toast.makeText(this, "Se ha reservado : " + CongresoApplication.restaurante.nombre, Toast.LENGTH_SHORT).show()

                } else {

                    Toast.makeText(this, "Ya está reservado!!", Toast.LENGTH_SHORT).show()

                }

            }

        } catch (e: Exception) {

            Log.i("error", e.message.toString())

        }

    }

    private fun comprobar(): Boolean {

        for (bono in CongresoApplication.bonos) {

            if (bono.puestoComida.id == CongresoApplication.restaurante.id) {

                idBono = bono.id!!

                return true
            }

        }

        return false
    }

}