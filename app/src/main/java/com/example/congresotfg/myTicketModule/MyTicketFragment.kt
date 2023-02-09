package com.example.congresotfg.myTicketModule

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.EntradaEntity
import com.example.congresotfg.common.retrofit.metodos.EntradaMethods
import com.example.congresotfg.databinding.FragmentMyTicketBinding
import com.example.congresotfg.common.utils.CorrutinaClass

class MyTicketFragment : Fragment() {

    private lateinit var binding: FragmentMyTicketBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyTicketBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        getEntrada()

    }

    private fun getEntrada() {

        val id_asistente = CongresoApplication.asistente.id

        CorrutinaClass().executeAction(requireActivity()) {

            val entrada = EntradaMethods().getEntrada(id_asistente)

            setUITicket(entrada)

        }

    }

    private fun setUITicket(entrada: EntradaEntity) {

        binding.tietNombreAsistente.text = CongresoApplication.asistente.nombre.editable()
        binding.tietApellidoAsistente.text = CongresoApplication.asistente.apellido.editable()
        binding.tietFechaTicketAsistente.text = entrada.fechaCompra.editable()

    }

    private fun String.editable(): Editable = Editable.Factory.getInstance().newEditable(this)

}