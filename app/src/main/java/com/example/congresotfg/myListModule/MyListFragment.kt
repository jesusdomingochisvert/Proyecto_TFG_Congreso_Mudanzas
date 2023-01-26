package com.example.congresotfg.myListModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.FragmentMyListBinding
import com.example.congresotfg.eventoInfoModule.EventoInfoActivity
import com.example.congresotfg.myListModule.adapter.MyListEventsAdapter
import com.example.congresotfg.myListModule.adapter.MyListRestaurantsAdapter
import com.example.congresotfg.myListModule.viewModel.MyListViewModel
import com.example.congresotfg.restauranteDialogModule.RestauranteDialogActivity

class MyListFragment : Fragment(), OnClickListener, SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentMyListBinding

    private lateinit var myListEventsAdapter: MyListEventsAdapter
    private lateinit var myListRestaurantsAdapter: MyListRestaurantsAdapter

    private lateinit var fragmentContext: Context

    private lateinit var linearLayoutManagerEvents: RecyclerView.LayoutManager
    private lateinit var linearLayoutManagerRestaurants: RecyclerView.LayoutManager

    private lateinit var search: SearchView

    private lateinit var myListViewModel: MyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        myListViewModel = ViewModelProvider(requireActivity())[MyListViewModel::class.java]

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyListBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        setupRecyclerView()

        setupSearchView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)



    }

    private fun setupSearchView() {

        search = binding.svBusquedaMyEvents

        search.setOnQueryTextListener(this)

    }

    private fun setupRecyclerView() {

        binding.btnEventos.setOnClickListener {

            myListEventsAdapter = MyListEventsAdapter(mutableListOf(),this)

            linearLayoutManagerEvents = LinearLayoutManager(fragmentContext)

            //getEventos()

            setupEventosViewModel()

            binding.rvMyEvents.apply {

                layoutManager = linearLayoutManagerEvents

                adapter = myListEventsAdapter

                binding.rvMyRestaurants.visibility = View.GONE

            }

        }

        binding.btnRestaurantes.setOnClickListener {

            myListRestaurantsAdapter = MyListRestaurantsAdapter(mutableListOf(),this)

            linearLayoutManagerRestaurants = LinearLayoutManager(fragmentContext)

            //getRestaurantes()

            setupRestaurantesViewModel()

            binding.rvMyRestaurants.apply {

                layoutManager = linearLayoutManagerRestaurants

                adapter = myListRestaurantsAdapter

                visibility = View.VISIBLE

            }

        }

    }

    private fun setupEventosViewModel() {

        myListViewModel.getEventos().observe(viewLifecycleOwner) { eventos ->

            myListEventsAdapter.setEvento(eventos)

        }

    }

    private fun setupRestaurantesViewModel() {

        myListViewModel.getRestaurantes().observe(viewLifecycleOwner) { restaurantes ->

            myListRestaurantsAdapter.setRestaurante(restaurantes)

        }

    }

    private fun getEventos() : MutableList<EventoEntity> {

        val eventos = mutableListOf<EventoEntity>()

        val evento = EventoEntity(1, 1, "Evento_1", "Descripción_1", "Lugar_1", "01:00", "02:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")
        val evento2 = EventoEntity(2, 1, "Evento_2", "Descripción_2", "Lugar_2", "02:00", "03:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")
        val evento3 = EventoEntity(3, 1, "Evento_3", "Descripción_3", "Lugar_3", "03:00", "04:00", "https://grandluxorhotels.com/wp-content/uploads/2016/09/9323706488_7c288a9659_b.jpg")

        eventos.add(evento)
        eventos.add(evento2)
        eventos.add(evento3)

        myListEventsAdapter.setEvento(eventos)

        return eventos

    }

    private fun getRestaurantes() : MutableList<RestauranteEntity> {

        val restaurantes = mutableListOf<RestauranteEntity>()

        val restaurante = RestauranteEntity(1, 1, "Restaurante_1", "Tipo_1", "Lugar_1", "Descripción_1", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")
        val restaurante2 = RestauranteEntity(2, 1, "Restaurante_2", "Tipo_2", "Lugar_2", "Descripción_2", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")
        val restaurante3 = RestauranteEntity(3, 1, "Restaurante_3", "Tipo_3", "Lugar_3", "Descripción_3", "https://e00-expansion.uecdn.es/assets/multimedia/imagenes/2019/06/25/15614775255199.jpg")

        restaurantes.add(restaurante)
        restaurantes.add(restaurante2)
        restaurantes.add(restaurante3)

        myListRestaurantsAdapter.setRestaurante(restaurantes)

        return restaurantes

    }

    override fun onClickEvento(eventoEntity: EventoEntity) {

        val intent = Intent(fragmentContext, EventoInfoActivity::class.java)

        startActivity(intent)

    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {

        val intent = Intent(fragmentContext, RestauranteDialogActivity::class.java)

        startActivity(intent)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return false

    }

    override fun onQueryTextChange(newText: String): Boolean {

        filterList(newText)

        return true

    }

    private fun filterList(newText: String) {

        myListViewModel.getEventos().observe(viewLifecycleOwner) { eventos ->

            val filteredListEventos = mutableListOf<EventoEntity>()

            for (e in eventos) {

                if (e.nombre.lowercase().contains(newText.lowercase())) {

                    filteredListEventos.add(e)

                }

            }

            if (filteredListEventos.isEmpty()) {

                Toast.makeText(fragmentContext, "No data found", Toast.LENGTH_SHORT).show()

            } else {

                myListEventsAdapter.setFilteredList(filteredListEventos)

            }

        }

        myListViewModel.getRestaurantes().observe(viewLifecycleOwner) { restaurantes ->

            val filteredListRestaurantes = mutableListOf<RestauranteEntity>()

            for (r in restaurantes) {

                if (r.nombre.lowercase().contains(newText.lowercase())) {

                    filteredListRestaurantes.add(r)

                }

            }

            if (filteredListRestaurantes.isEmpty()) {

                Toast.makeText(fragmentContext, "No data found", Toast.LENGTH_SHORT).show()

            } else {

                myListRestaurantsAdapter.setFilteredList(filteredListRestaurantes)

            }

        }

    }

}