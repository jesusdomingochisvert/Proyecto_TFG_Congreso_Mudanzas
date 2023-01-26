package com.example.congresotfg.LoginModule


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.example.congresotfg.LoginModule.model.AsistenteInfo
import com.example.congresotfg.LoginModule.model.LoginService
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.databinding.ActivityLoginBinding
import com.example.congresotfg.mainModule.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
                login()
        }


    }

    private fun login() {

        val username = binding.etUsername.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.CONGRESO_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LoginService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = service.loginAsistente(AsistenteInfo(username,password))
                Log.i("hola",result.body().toString())
                if(result.toString() != "null"){
                    entrar()
                }

            } catch(e: Exception) {
                (e as? HttpException)?.let {
                    when(it.code()) {
                        400 -> { error(getString(R.string.main_error_server)) }
                        else -> error(getString(R.string.main_error_response))
                    }
                }

            }
        }
    }

    private fun entrar() {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun error(result: String) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show()
    }




    //private fun bienvenida() {Toast.makeText(this,"Bienvenido/a",Toast.LENGTH_SHORT).show()}








}