package com.example.congresotfg.partnersFragmentModule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.metodos.PartnerMethods
import com.example.congresotfg.databinding.FragmentPartnersBinding

import com.example.congresotfg.partnersFragmentModule.adapter.PartnersAdapter
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.common.utils.listeners.SocioListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PartnersFragment : Fragment(), SocioListener {

    private lateinit var fragmentContext: Context

    private lateinit var partnersAdapter: PartnersAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var binding: FragmentPartnersBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentPartnersBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        partnersAdapter = PartnersAdapter(this)

    }

    override fun onStart() {

        super.onStart()

        getAllPartners()

        setupRecyclerView()

    }

    private fun getAllPartners(){

        CorrutinaClass().executeAction(fragmentContext) {

            val socios = PartnerMethods().getPartners()

            partnersAdapter.submitList(socios)

            binding.progressbar.visibility= View.GONE

        }

    }

    private fun setupRecyclerView() {

        linearLayoutManager = LinearLayoutManager(fragmentContext)

        binding.rvPartners.apply {

            layoutManager = linearLayoutManager

            adapter = partnersAdapter

        }

    }

    override fun onClickSocio(socioEntity: SocioEntity) {

        val items = arrayOf("Ir a la web de la empresa", "Contactar")

        MaterialAlertDialogBuilder(requireActivity()).setItems(items) { _, i ->

                when (i) {

                    1 -> abrirCorreo(socioEntity.asistente.correo)

                    0 -> abrirWeb(socioEntity.empresa.enlace)

                }

        }.show()

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