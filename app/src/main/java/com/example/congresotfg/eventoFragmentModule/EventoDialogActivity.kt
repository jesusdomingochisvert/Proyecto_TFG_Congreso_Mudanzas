package com.example.congresotfg.eventoFragmentModule

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.congresotfg.R
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EventoDialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEventoDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tituloEvento.setText("DREAMHACK VALENCIA")
        binding.lugarEvento.setText("ZONA SUPERIOR A")
        binding.btnBack.setOnClickListener(){
            onBackPressed()
        }

    }
}