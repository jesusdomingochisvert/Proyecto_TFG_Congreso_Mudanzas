package com.example.congresotfg.myTicketModule

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EntradaEntity
import com.example.congresotfg.databinding.FragmentMyTicketBinding
import com.example.congresotfg.myTicketModule.model.EntradaResponse
import com.example.congresotfg.myTicketModule.viewModel.EntradaViewModel

class MyTicketFragment : Fragment() {

    private lateinit var binding: FragmentMyTicketBinding

    private lateinit var entradaViewModel: EntradaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        entradaViewModel = ViewModelProvider(this)[EntradaViewModel::class.java]

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

        val id_asistente = CongresoApplication.asistente.id

        entradaViewModel.getEntrada(id_asistente)

        entradaViewModel.entradaInfo.observe(viewLifecycleOwner, Observer { entrada ->

            setUITicket(entrada)

        })

    }

    private fun setUITicket(entrada: EntradaResponse) {

        binding.tietNombreAsistente.text = CongresoApplication.asistente.nombre.editable()
        binding.tietApellidoAsistente.text = CongresoApplication.asistente.apellido.editable()
        binding.tietFechaTicketAsistente.text = entrada.fecha.editable()

    }

    private fun String.editable(): Editable = Editable.Factory.getInstance().newEditable(this)

}