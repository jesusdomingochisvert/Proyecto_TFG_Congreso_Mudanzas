package com.example.congresotfg.profileModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.retrofit.metodos.*
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.RestauranteListener
import com.example.congresotfg.databinding.FragmentProfileBinding
import com.example.congresotfg.profileModule.adapter.BonosListAdapter
import com.example.congresotfg.profileModule.adapter.EditProfileDialog
import com.example.congresotfg.restauranteInfoModule.RestauranteInfoActivity

class ProfileFragment : Fragment(),RestauranteListener {

    private lateinit var binding: FragmentProfileBinding

    private lateinit var fragmentContext: Context

    private lateinit var bonosListAdapter: BonosListAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    //private var listaBonos = mutableListOf<RestauranteEntity>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupProfile()

        setupRecyclerView()

    }

    override fun onResume() {

        super.onResume()

        setupProfile()

    }

    private fun setupProfile() {

        with(binding){

            CorrutinaClass().executeAction(requireContext()) {

                val entradaEntity = EntradaMethods().getEntrada(CongresoApplication.asistente.id)

                if (CongresoApplication.socio) {

                    val socio = LoginMethods().comprobarSocio(CongresoApplication.asistente.id)

                    cvlogo.visibility = VISIBLE

                    ImageClass().loadImage(socio.empresa.logo, logoempresa, fragmentContext)

                }

                txtFechaCompra.text = entradaEntity.fechaCompra

                ImageClass().loadImage(CongresoApplication.asistente.imagen, imagen, fragmentContext)

                titFechaCompra.visibility = VISIBLE

                nombreAsistente.text = CongresoApplication.asistente.nombreUsuario.uppercase()

                rellenarBonos()

            }

            fabEditProfile.setOnClickListener {

                editProfile()

            }

        }

    }

    private fun editProfile() {

        val intent = Intent(fragmentContext, EditProfileDialog::class.java)

        startActivity(intent)

    }

    private fun rellenarBonos(){

        binding.contadorBonos.text= CongresoApplication.bonos.size.toString()

        bonosListAdapter.submitList(CongresoApplication.bonos)

    }

    private fun setupRecyclerView() {
        bonosListAdapter = BonosListAdapter(this)
        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.rvBonos.apply {
            layoutManager = linearLayoutManager
            adapter = bonosListAdapter
        }
    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {
        val intent = Intent(fragmentContext, RestauranteInfoActivity::class.java)
        intent.putExtra("id_restaurante", restauranteEntity.id)
        startActivity(intent)
    }
}