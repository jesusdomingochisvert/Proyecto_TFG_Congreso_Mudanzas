package com.example.congresotfg.myTicketModule

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.databinding.FragmentAboutBinding
import com.example.congresotfg.databinding.FragmentMyTicketBinding
import com.example.congresotfg.myTicketModule.model.MyTicketInteractor
import com.example.congresotfg.myTicketModule.viewModel.MyTicketViewModel
import com.google.gson.Gson

class MyTicketFragment : Fragment() {

    private lateinit var binding: FragmentMyTicketBinding

    private lateinit var myTicketViewModel: MyTicketViewModel

    private var asistente: AsistenteEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        myTicketViewModel = ViewModelProvider(requireActivity())[MyTicketViewModel::class.java]

    }

    override fun onStart() {
        super.onStart()

        val preferences = requireActivity().getSharedPreferences("global", Context.MODE_PRIVATE)

        val gson = Gson()

        val json = preferences.getString("asistente", "")

        asistente = gson.fromJson(json, AsistenteEntity::class.java)

        myTicketViewModel.setAsistente(asistente!!)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyTicketBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

    }

    private fun setupViewModel() {

        myTicketViewModel.getAsistente().observe(viewLifecycleOwner) {

            setUITicket(it)

        }

    }

    private fun String.editable(): Editable = Editable.Factory.getInstance().newEditable(this)

    private fun setUITicket(asistenteEntity: AsistenteEntity) {

        binding.tietNombreAsistente.text = asistenteEntity.nombre.editable()
        binding.tietApellidoAsistente.text = asistenteEntity.apellidos.editable()


    }

}