package com.example.congresotfg.partnersFragmentModule

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.EmpresaEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.databinding.FragmentPartnersBinding
import com.example.congresotfg.eventoFragmentModule.EventoDialogActivity
import com.example.congresotfg.homeModule.viewModel.PartnersViewModel
import com.example.congresotfg.partnersFragmentModule.adapter.PartnersAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PartnersFragment : Fragment(),SocioListener {


    private lateinit var fragmentContext: Context

    private lateinit var partnersAdapter: PartnersAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var partnersViewModel: PartnersViewModel

    private lateinit var binding: FragmentPartnersBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        partnersViewModel = ViewModelProvider(requireActivity())[PartnersViewModel::class.java]

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentPartnersBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        setupRecyclerView()

    }

    private fun setupViewModel() {

        partnersViewModel.getSocios().observe(viewLifecycleOwner) { socios ->

            partnersAdapter.setSocio(socios)

        }
    }

    private fun setupRecyclerView() {

        partnersAdapter = PartnersAdapter(mutableListOf(), this)

        linearLayoutManager = LinearLayoutManager(fragmentContext)


        binding.rvPartners.apply {

            layoutManager = linearLayoutManager

            adapter = partnersAdapter

        }

    }

    override fun onLongClickSocio(socioEntity: SocioEntity) {
        val items = arrayOf("Ir a la web de la empresa", "Contactar")
        MaterialAlertDialogBuilder(requireActivity())
            .setItems(items) { _, i ->
                when (i) {
                    1 -> abrirCorreo(socioEntity.asistente.correo)
                    0 -> abrirWeb(socioEntity.empresa.enlace)
                    
                }
            }
            .show()
    }

    private fun abrirCorreo(correo: String) {

            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))

            intent.setType("message/rfc822")

            startActivity(Intent.createChooser(intent, "Elige un cliente de Correo:"))

        }


    private fun abrirWeb(enlace: String) {
        if (enlace.isEmpty()) {
            Toast.makeText(requireActivity(), R.string.main_error_no_website, Toast.LENGTH_LONG).show()
        } else {
            val websiteIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(enlace)
            }

            startActivity(websiteIntent)
        }
    }


}