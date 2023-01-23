package com.example.congresotfg.homeModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.ItemEatZonesBinding

class HomeRestauranteAdapter(private var restaurantes: MutableList<RestauranteEntity>, private var listener: OnClickListener): RecyclerView.Adapter<HomeRestauranteAdapter.ViewHolder>() {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_eat_zones, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurante = restaurantes.get(position)

        with(holder) {

            setListener(restaurante)

            with(binding) {

                rvEatZonesNombreEatZone.text = restaurante.nombre

            }

            Glide.with(fragmentContext)
                .load(restaurante.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgEatZones)

        }

    }

    override fun getItemCount(): Int = restaurantes.size

    fun setRestaurante(restaurantes: MutableList<RestauranteEntity>) {

        this.restaurantes = restaurantes

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemEatZonesBinding.bind(view)

        fun setListener(restauranteEntity: RestauranteEntity) {

            with(binding) {

                root.setOnClickListener {

                    listener.onClickRestaurante(restauranteEntity)

                }

            }

        }

    }

}