package com.example.congresotfg.LoginModule


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.congresotfg.mainModule.MainActivity
import com.example.congresotfg.R
import com.example.congresotfg.databinding.ActivityLoginBinding


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



        binding.btnLogin.setOnClickListener() {

            startActivity(Intent(this, MainActivity::class.java))

        }


    }

}