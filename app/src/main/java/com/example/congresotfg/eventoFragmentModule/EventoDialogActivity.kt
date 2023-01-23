package com.example.congresotfg.eventoFragmentModule

import android.app.Dialog
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.congresotfg.R
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EventoDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventoDialogBinding

    private lateinit var homeViewModel: HomeViewModel

    private var preferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        preferences = getSharedPreferences("evento", MODE_PRIVATE)

        binding.tituloEvento.setText("DREAMHACK VALENCIA")
        binding.lugarEvento.setText("ZONA SUPERIOR A")
        binding.btnBack.setOnClickListener(){
            onBackPressed()
        }

        setupEvento()

    }

    private fun setupEvento() {

        val id = preferences?.getString("id", "")

        if (id != null) {

            homeViewModel.loadEvento(id)

        }

    }

}