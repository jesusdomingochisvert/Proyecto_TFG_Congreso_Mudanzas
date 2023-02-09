package com.example.congresotfg.profileModule.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.R
import com.example.congresotfg.common.retrofit.metodos.AsistenteMethods
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.databinding.ActivityEditProfileDialogBinding
import com.example.congresotfg.databinding.ActivityEventoDialogBinding

class EditProfileDialog : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityEditProfileDialogBinding.inflate(layoutInflater)

        setContentView(binding.root)

        edit()

    }

    private fun edit() {

        binding.tietEditUsername.text = CongresoApplication.asistente.nombreUsuario.editable()
        binding.tietEditPassword.text = CongresoApplication.asistente.contrasenya.editable()
        binding.tietEditEmail.text = CongresoApplication.asistente.correo.editable()

        binding.btnEdit.setOnClickListener {

            val username = binding.tietEditUsername.text.toString().trim()
            val password = binding.tietEditPassword.text.toString().trim()
            val email = binding.tietEditEmail.text.toString().trim()

            CorrutinaClass().executeAction(this) {

                AsistenteMethods().putAsistente(username, password, email)

            }

            if (username.isNotEmpty()) {

                CongresoApplication.asistente.nombreUsuario = username

            }

            if (password.isNotEmpty()) {

                CongresoApplication.asistente.contrasenya = password

            }

            if (email.isNotEmpty()) {

                CongresoApplication.asistente.correo = email

            }

            finish()

        }

        binding.btnCancelEdit.setOnClickListener {

            finish()

        }

    }

    private fun String.editable(): Editable = Editable.Factory.getInstance().newEditable(this)

}