package com.example.congresotfg.homeModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.RestauranteEntity
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.RestauranteListener
import com.example.congresotfg.databinding.ItemEatZonesBinding

class HomeRestauranteListAdapter(private var listener: RestauranteListener):
    ListAdapter<RestauranteEntity, RecyclerView.ViewHolder>(RestauranteDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_eat_zones, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val restaurante = getItem(position)

        with(holder as ViewHolder) {

            setListener(restaurante)

            with(binding) {

                rvEatZonesNombreEatZone.text = restaurante.nombre.toUpperCase()

                ImageClass().loadImage(restaurante.imagen, imgEatZones, fragmentContext)

            }

        }

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

    class RestauranteDiffCallback: DiffUtil.ItemCallback<RestauranteEntity>() {
        override fun areItemsTheSame(oldItem: RestauranteEntity, newItem: RestauranteEntity): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: RestauranteEntity, newItem: RestauranteEntity): Boolean {

            return oldItem == newItem

        }


    }

}