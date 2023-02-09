package com.example.congresotfg.myListModule

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.common.entities.ActividadEntity
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.retrofit.metodos.EventoMethods
import com.example.congresotfg.common.retrofit.metodos.RestauranteMethods
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.common.utils.listeners.EventoListener
import com.example.congresotfg.common.utils.listeners.RestauranteListener
import com.example.congresotfg.databinding.FragmentMyListBinding
import com.example.congresotfg.eventoInfoModule.EventoInfoActivity
import com.example.congresotfg.myListModule.adapter.MyListEventsAdapter
import com.example.congresotfg.myListModule.adapter.MyListRestaurantsAdapter
import com.example.congresotfg.restauranteInfoModule.RestauranteInfoActivity
import java.util.EnumSet.range

class MyListFragment : Fragment(), EventoListener, RestauranteListener, SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentMyListBinding

    private lateinit var myListEventsAdapter: MyListEventsAdapter
    private lateinit var myListRestaurantsAdapter: MyListRestaurantsAdapter

    private lateinit var fragmentContext: Context

    private lateinit var linearLayoutManagerEvents: RecyclerView.LayoutManager
    private lateinit var linearLayoutManagerRestaurants: RecyclerView.LayoutManager

    private lateinit var search: SearchView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyListBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()

        setupRecyclerView()

        setupSearchView()

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        myListEventsAdapter = MyListEventsAdapter(mutableListOf(),this)

        myListRestaurantsAdapter = MyListRestaurantsAdapter(mutableListOf(),this)

    }

    private fun setupSearchView() {

        search = binding.svBusquedaMyEvents

        search.setOnQueryTextListener(this)

    }

    private fun setupRecyclerView() {

        binding.btnEventos.setOnClickListener {

            linearLayoutManagerEvents = LinearLayoutManager(fragmentContext)

            showEventos()

            binding.rvMyEvents.apply {

                layoutManager = linearLayoutManagerEvents

                adapter = myListEventsAdapter

                binding.rvMyRestaurants.visibility = View.GONE

                visibility = View.VISIBLE

            }

        }

        binding.btnRestaurantes.setOnClickListener {

            linearLayoutManagerRestaurants = LinearLayoutManager(fragmentContext)

            showRestaurantes()

            binding.rvMyRestaurants.apply {

                layoutManager = linearLayoutManagerRestaurants

                adapter = myListRestaurantsAdapter

                binding.rvMyEvents.visibility = View.GONE

                visibility = View.VISIBLE

            }

        }

    }

    private fun showEventos() {

        CorrutinaClass().executeAction(fragmentContext) {

            val eventos = EventoMethods().getAsistenteEventos()

            CongresoApplication.eventosLike = eventos

            myListEventsAdapter.setEvento(eventos)

        }

    }

    private fun showRestaurantes() {

        CorrutinaClass().executeAction(fragmentContext) {

            val restaurantes = RestauranteMethods().getRestaurantes()

            rellenarMyList(restaurantes)

            myListRestaurantsAdapter.setRestaurante(CongresoApplication.restauranteLike)

        }

    }

    private fun rellenarMyList(restaurantes: MutableList<RestauranteEntity>?) {

        val lista = mutableListOf<RestauranteEntity>()

        for (b in CongresoApplication.bonos) {

            lista.add(b.puestoComida)

        }

        for (r in restaurantes!!) {

            if (!lista.contains(r)) {

                if (!CongresoApplication.restauranteLike.contains(r)) {

                    CongresoApplication.restauranteLike.add(r)

                }

            }

        }

    }

    override fun onClickEvento(eventoEntity: EventoEntity) {

        val intent = Intent(fragmentContext, EventoInfoActivity::class.java)

        intent.putExtra("id_evento", eventoEntity.id)

        startActivity(intent)

    }

    override fun onClickRestaurante(restauranteEntity: RestauranteEntity) {

        val intent = Intent(fragmentContext, RestauranteInfoActivity::class.java)

        intent.putExtra("id_restaurante", restauranteEntity.id)

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

        val filteredListEventos = mutableListOf<EventoEntity>()

        for (e in CongresoApplication.eventosLike) {

            if (e.nombre.lowercase().contains(newText.lowercase())) {

                filteredListEventos.add(e)

            }

        }

        myListEventsAdapter.setFilteredList(filteredListEventos)

        val filteredListRestaurantes = mutableListOf<RestauranteEntity>()

        for (r in CongresoApplication.restauranteLike) {

            if (r.nombre.lowercase().contains(newText.lowercase())) {

                filteredListRestaurantes.add(r)

            }

        }

        myListRestaurantsAdapter.setFilteredList(filteredListRestaurantes)

    }

}
