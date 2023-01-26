package com.example.congresotfg.eventoInfoModule

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.example.congresotfg.eventoInfoModule.viewModel.EventoInfoViewModel

class EventoInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventoDialogBinding

    private lateinit var eventoDialogViewModel: EventoInfoViewModel

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventoDialogViewModel = ViewModelProvider(this)[EventoInfoViewModel::class.java]

        binding.lugarEvento.setText("ZONA SUPERIOR A")

        binding.btnBack.setOnClickListener(){
            onBackPressed()
        }

        setupViewModel()

    }

    private fun setupViewModel() {

        val id = intent.extras?.getLong("id")

        eventoDialogViewModel.getEvento(id!!)

        eventoDialogViewModel.eventoInfo.observe(this, Observer { evento ->

            binding.tituloEvento.text = evento.nombre

            Glide.with(this).load(evento.imagen).into(binding.imgEvento)

        })

    }

}