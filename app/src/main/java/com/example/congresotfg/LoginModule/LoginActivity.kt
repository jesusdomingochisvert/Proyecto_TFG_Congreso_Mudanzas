package com.example.congresotfg.LoginModule


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.congresotfg.mainModule.MainActivity
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.databinding.ActivityLoginBinding
import com.google.gson.Gson


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferences = getSharedPreferences("global", MODE_PRIVATE)

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // In landscape
            val newLayoutParams = binding.cajalogin.getLayoutParams() as ConstraintLayout.LayoutParams
            newLayoutParams.topMargin = 50
            newLayoutParams.leftMargin = 200
            newLayoutParams.rightMargin = 200
            newLayoutParams.bottomMargin = 160
            binding.cajalogin.setLayoutParams(newLayoutParams)
        }



        binding.btnLogin.setOnClickListener {

            val asistente = AsistenteEntity(id = 1, nombre = "Jesús", apellidos = "Domingo")

            val gson = Gson()

            val json = gson.toJson(asistente)

            val edit = preferences!!.edit()

            edit.putString("asistente", json).apply()

            startActivity(Intent(this, MainActivity::class.java))

        }


    }

}