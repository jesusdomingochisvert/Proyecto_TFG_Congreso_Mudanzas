package com.example.congresotfg.LoginModule


import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.metodos.ActividadMethods
import com.example.congresotfg.common.retrofit.metodos.BonoMethods
import com.example.congresotfg.common.retrofit.metodos.LoginMethods
import com.example.congresotfg.common.retrofit.service.LoginService
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.databinding.ActivityLoginBinding
import com.example.congresotfg.eventoInfoModule.EventoInfoActivity
import com.example.congresotfg.homeModule.HomeFragment
import com.example.congresotfg.mainModule.MainActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

import java.net.UnknownHostException


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            cambiarTamañoCaja()
        }

        comprobarCheck(getRecordarSP())

        binding.btnLogin.setOnClickListener {

            login()

        }
    }

    private fun comprobarCheck(r:SharedPreferences){
        val recordar = r.getString("recordar","")
        if(recordar=="true"){
            val id = r.getString("id_usuario","")
            CorrutinaClass().executeAction(this@LoginActivity) {
                CongresoApplication.asistente = LoginMethods().getAsistente(id!!.toLong())
                comprobarSocio(CongresoApplication.asistente)
                generarBonos()
            }
            entrar() }
    }

    private fun login(){

        if (comprobarTextInputs()){

            CorrutinaClass().executeAction(this) {

                if(comprobarAsistenteApi()){

                    if(binding.checkbox.isChecked){

                        recordarUsuario()

                    }else{

                        olvidarUsuario()

                    }

                    entrar()

                }

            }

        }

    }

    private suspend fun generarBonos() {

        CorrutinaClass().executeAction(this){

            val bonos = BonoMethods().getBonosAsistente()

            CongresoApplication.bonos = bonos

        }

    }

    private fun entrar() {

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

    }

    suspend fun comprobarAsistenteApi() : Boolean{

        var existe = false
        val nombre = binding.etUsername.text.toString().trim()
        val contrasenya = binding.etPassword.text.toString().trim()

        try {
            val asistente = LoginMethods().comprobarAsistente(nombre, contrasenya)
            CongresoApplication.asistente = asistente
            existe = true
            comprobarSocio(asistente)
            generarBonos()
        } catch (e: Exception) {
            (e as? KotlinNullPointerException)?.let {
                Toast.makeText(this, "Ese usuario no existe", Toast.LENGTH_SHORT).show()
            }
            (e as? UnknownHostException)?.let {
                Toast.makeText(this, "No tienes conexión a internet", Toast.LENGTH_SHORT).show()
            }
        }
        return existe
    }
    private fun comprobarTextInputs() : Boolean{
        val nombretil = binding.tilUsuario
        val contrasenyatil = binding.tilContrasenya

        var valido = true

        val lista:List<TextInputLayout> = listOf(nombretil,contrasenyatil)
        for (inputLayout in lista){
            if (inputLayout.editText?.text!!.isEmpty()){
                inputLayout.helperText= "REQUERIDO"
                inputLayout.isHelperTextEnabled=true
                valido = false
            }else{
                inputLayout.isHelperTextEnabled=false
            }
        }
        return valido
    }

    private fun cambiarTamañoCaja() {
        // In landscape
        val newLayoutParams = binding.cajalogin.getLayoutParams() as ConstraintLayout.LayoutParams
        newLayoutParams.topMargin = 50
        newLayoutParams.leftMargin = 200
        newLayoutParams.rightMargin = 200
        newLayoutParams.bottomMargin = 160
        binding.cajalogin.setLayoutParams(newLayoutParams)
    }

    private fun getRecordarSP() : SharedPreferences{
        val recordar = getSharedPreferences("recordar", MODE_PRIVATE)
        return recordar
    }
    private fun recordarUsuario(){
        val edit: SharedPreferences.Editor = getRecordarSP().edit()
        edit.putString("recordar","true")
        edit.putString("id_usuario",CongresoApplication.asistente.id.toString())
        edit.apply()
    }

    private fun olvidarUsuario(){
        val edit: SharedPreferences.Editor = getRecordarSP().edit()
        edit.clear()
        edit.apply()
    }

    suspend fun comprobarSocio(asistente: AsistenteEntity){
        try {
            LoginMethods().comprobarSocio(asistente.id)
            hacerSocio()
            CongresoApplication.socio = true
            Toast.makeText(this, "ES SOCIO", Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(this, "ES AJENO", Toast.LENGTH_SHORT).show()
            sacardeSocio()
        }
    }

    private fun sacardeSocio() {
        val socio = getSharedPreferences("socio", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = socio.edit()
        edit.putBoolean("socio",false)
        edit.apply()
    }

    private fun hacerSocio() {
        val socio = getSharedPreferences("socio", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = socio.edit()
        edit.putBoolean("socio",true)
        edit.apply()
    }
}