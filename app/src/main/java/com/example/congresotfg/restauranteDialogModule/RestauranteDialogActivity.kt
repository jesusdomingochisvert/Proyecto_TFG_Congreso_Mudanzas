package com.example.congresotfg.restauranteDialogModule

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.congresotfg.R
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.example.congresotfg.databinding.ActivityRestauranteDialogBinding

class RestauranteDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestauranteDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nombreRestaurante.setText("D'Guste Mediterráneo")
        binding.descropcionTxt.setText("La cocina mediterránea es algo que no hay que dejar de probar en este restaurante. Muchos clientes remarcan que en este establecimiento el personal es muy activo.")
        binding.lugarTxt.setText("PABELLON A")
        binding.tipocomidaTxt.setText("Mediterránea e italiana")

        binding.btnBack.setOnClickListener(){
            onBackPressed()
        }
    }
}