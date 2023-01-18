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
import com.example.congresotfg.databinding.FragmentHomeBinding
import com.example.congresotfg.databinding.FragmentMyTicketBinding
import com.example.congresotfg.databinding.FragmentPartnersBinding
import com.example.congresotfg.eventoFragmentModule.EventoDialogActivity
import com.example.congresotfg.homeModule.adapter.HomeEventoAdapter
import com.example.congresotfg.homeModule.adapter.HomeRestauranteAdapter
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.example.congresotfg.homeModule.viewModel.PartnersViewModel
import com.example.congresotfg.partnersFragmentModule.adapter.PartnersAdapter

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

    override fun onClickSocio(socioEntity: SocioEntity) {
        val intent = Intent(fragmentContext, EventoDialogActivity::class.java)

        startActivity(intent)

    }

    override fun onClickEmpresa(empresaEntity: EmpresaEntity) {
        val websiteIntent = Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse(empresaEntity.enlace)
        }
        try{
            startActivity(websiteIntent)
        }catch (error: ActivityNotFoundException){
            Toast.makeText(requireActivity(), R.string.main_error_website, Toast.LENGTH_LONG).show()
        }
    }

}