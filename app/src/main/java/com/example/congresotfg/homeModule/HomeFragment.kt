package com.example.congresotfg.homeModule

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.common.entities.EmpresaEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.FragmentHomeBinding
import com.example.congresotfg.eventoInfoModule.EventoDialogActivity
import com.example.congresotfg.homeModule.adapter.HomeEventoListAdapter
import com.example.congresotfg.homeModule.adapter.HomeRestauranteAdapter
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.example.congresotfg.restauranteDialogModule.RestauranteDialogActivity
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class HomeFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var fragmentContext: Context

    private lateinit var homeEventoListAdapter: HomeEventoListAdapter
    private lateinit var homeRestauranteAdapter: HomeRestauranteAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        setupViewModel()

        setupRecyclerView()
        setupRecyclerView2()

        setupImagesCarousel()

    }

    private fun setupViewModel() {

        homeViewModel.getEventos().observe(viewLifecycleOwner) { eventos ->

            eventos.sort()

            homeEventoListAdapter.submitList(eventos)

        }

        homeViewModel.getRestaurantes().observe(viewLifecycleOwner) { restaurantes ->

            restaurantes.sort()

            homeRestauranteAdapter.setRestaurante(restaurantes)

        }

    }

    private fun setupRecyclerView() {

        homeEventoListAdapter = HomeEventoListAdapter(this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.rvRecentEvents.apply {

            layoutManager = linearLayoutManager

            adapter = homeEventoListAdapter

        }

    }

    private fun setupRecyclerView2() {

        homeRestauranteAdapter = HomeRestauranteAdapter(mutableListOf(), this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.rvEatZones.apply {

            layoutManager = linearLayoutManager

            adapter = homeRestauranteAdapter

        }

    }

    private fun setupImagesCarousel() {

        val images = mutableListOf<CarouselItem>()
        val lista = mutableListOf<EmpresaEntity>()

        val carousel = binding.imgCarouselPublicidad

        homeViewModel.getPatrocinadores().observe(viewLifecycleOwner) { patrocinadores ->
            for (p in patrocinadores){
                val imagen = CarouselItem(p.empresaCif.logo,p.empresaCif.nombre)
                images.add(imagen)
                lista.add(p.empresaCif)

            }
            carousel.addData(images)


        }
        carousel.setOnClickListener(){
            Toast.makeText(requireActivity(),carousel.imageViewId.toString()
                ,Toast.LENGTH_SHORT).show()
        }


    }

    override fun onClickEvento(eventoEntity: EventoEntity) {

        val intent = Intent(fragmentContext, EventoDialogActivity::class.java)

        intent.putExtra("id", eventoEntity.id)

        startActivity(intent)

    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {

        val intent = Intent(fragmentContext, RestauranteDialogActivity::class.java)

        startActivity(intent)

    }

}