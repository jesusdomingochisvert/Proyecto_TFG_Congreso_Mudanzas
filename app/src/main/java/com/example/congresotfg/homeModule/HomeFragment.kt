package com.example.congresotfg.homeModule

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import com.example.congresotfg.common.utils.Constants
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.FragmentHomeBinding
import com.example.congresotfg.eventoInfoModule.EventoDialogActivity
import com.example.congresotfg.homeModule.adapter.HomeEventoListAdapter
import com.example.congresotfg.homeModule.adapter.HomeRestauranteListAdapter
import com.example.congresotfg.homeModule.model.EventosService
import com.example.congresotfg.homeModule.model.RestauranteService
import com.example.congresotfg.homeModule.viewModel.HomeViewModel
import com.example.congresotfg.restauranteDialogModule.RestauranteDialogActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment(), OnClickListener {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var fragmentContext: Context

    private lateinit var homeEventoListAdapter: HomeEventoListAdapter
    private lateinit var homeRestauranteListAdapter: HomeRestauranteListAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager


    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        homeViewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]


    }

    /*private fun launchAllEventsFragment(args:Bundle? = null) {
        val fragment = AllEventsFragment()
        if(args!=null) fragment.arguments = args
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.drawer_main,fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        fragmentContext = this.requireActivity()



        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        //setupViewModel()

        showEventos()
        showRestaurantes()

        setupRecyclerView()
        setupRecyclerView2()

        setupImagesCarousel()

    }

    private fun getRetrofit(): Retrofit {

        return Retrofit.Builder().baseUrl(Constants.CONGRESO_URL).addConverterFactory(GsonConverterFactory.create()).build()

    }

    private fun showEventos() {

        val service = getRetrofit().create(EventosService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val call = service.getEventos()

            withContext(Dispatchers.Main) {

                if (call.isSuccessful) {

                    val eventos = call.body()

                    eventos?.sorted()

                    homeEventoListAdapter.submitList(eventos)

                }

            }

        }

    }

    private fun showRestaurantes() {

        val service = getRetrofit().create(RestauranteService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            val call = service.getRestaurantes()

            withContext(Dispatchers.Main) {

                if (call.isSuccessful) {

                    val restaurantes = call.body()

                    homeRestauranteListAdapter.submitList(restaurantes)

                }

            }

        }

    }

    private fun setupViewModel() {

        homeViewModel.getEventos().observe(viewLifecycleOwner) { eventos ->

            eventos.sort()

            homeEventoListAdapter.submitList(eventos)

        }

        homeViewModel.getRestaurantes().observe(viewLifecycleOwner) { restaurantes ->

            restaurantes.sort()

            homeRestauranteListAdapter.submitList(restaurantes)

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

        homeRestauranteListAdapter = HomeRestauranteListAdapter(this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.rvEatZones.apply {

            layoutManager = linearLayoutManager

            adapter = homeRestauranteListAdapter

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