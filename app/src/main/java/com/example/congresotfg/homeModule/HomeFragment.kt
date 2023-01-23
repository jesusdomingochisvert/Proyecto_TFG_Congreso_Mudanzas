package com.example.congresotfg.homeModule

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock.sleep
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.common.entities.EmpresaEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.PatrocinadorEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.FragmentHomeBinding
import com.example.congresotfg.eventoFragmentModule.EventoDialogActivity
import com.example.congresotfg.homeModule.adapter.HomeEventoAdapter
import com.example.congresotfg.homeModule.adapter.HomeRestauranteAdapter
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.example.congresotfg.restauranteDialogModule.RestauranteDialogActivity
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import java.lang.Thread.sleep
import java.sql.Time

class HomeFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var fragmentContext: Context

    private lateinit var homeEventoAdapter: HomeEventoAdapter
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

            homeEventoAdapter.setEvento(eventos)

        }

        homeViewModel.getRestaurantes().observe(viewLifecycleOwner) { restaurantes ->

            homeRestauranteAdapter.setRestaurante(restaurantes)

        }

    }

    private fun setupRecyclerView() {

        homeEventoAdapter = HomeEventoAdapter(mutableListOf(), this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        //getEventos()

        binding.rvRecentEvents.apply {

            layoutManager = linearLayoutManager

            adapter = homeEventoAdapter

        }

    }

    private fun setupRecyclerView2() {

        homeRestauranteAdapter = HomeRestauranteAdapter(mutableListOf(), this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        //getRestaurantes()

        binding.rvEatZones.apply {

            layoutManager = linearLayoutManager

            adapter = homeRestauranteAdapter

        }

    }

    private fun getEventos() {

        val eventos = mutableListOf<EventoEntity>()

        val evento = EventoEntity(1, 1, "Evento_1", "Descripción_1", "Lugar_1", "01:00", "02:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")
        val evento2 = EventoEntity(2, 1, "Evento_2", "Descripción_2", "Lugar_2", "02:00", "03:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")
        val evento3 = EventoEntity(3, 1, "Evento_3", "Descripción_3", "Lugar_3", "03:00", "04:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")

        eventos.add(evento)
        eventos.add(evento2)
        eventos.add(evento3)

        homeEventoAdapter.setEvento(eventos)

    }

    private fun getRestaurantes() {

        val restaurantes = mutableListOf<RestauranteEntity>()

        val restaurante = RestauranteEntity(1, 1, "Restaurante_1", "Tipo_1", "Lugar_1", "Descripción_1", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")
        val restaurante2 = RestauranteEntity(2, 1, "Restaurante_2", "Tipo_2", "Lugar_2", "Descripción_2", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")
        val restaurante3 = RestauranteEntity(3, 1, "Restaurante_3", "Tipo_3", "Lugar_3", "Descripción_3", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")

        restaurantes.add(restaurante)
        restaurantes.add(restaurante2)
        restaurantes.add(restaurante3)

        homeRestauranteAdapter.setRestaurante(restaurantes)

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
        startActivity(intent)

    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {

        val intent = Intent(fragmentContext, RestauranteDialogActivity::class.java)

        startActivity(intent)

    }

}