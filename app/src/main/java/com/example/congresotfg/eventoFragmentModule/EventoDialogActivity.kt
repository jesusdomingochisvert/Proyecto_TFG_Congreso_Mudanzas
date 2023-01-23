package com.example.congresotfg.eventoFragmentModule

import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.congresotfg.R
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.example.congresotfg.eventoFragmentModule.viewModel.EventoFragmentViewModel
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EventoDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventoDialogBinding

    private lateinit var eventoDialogViewModel: EventoFragmentViewModel

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        eventoDialogViewModel = ViewModelProvider(this)[EventoFragmentViewModel::class.java]

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