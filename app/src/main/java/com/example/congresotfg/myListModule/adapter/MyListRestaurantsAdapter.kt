package com.example.congresotfg.myListModule.adapter

import android.annotation.SuppressLint
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
import com.example.congresotfg.databinding.ItemMyRestaurantsBinding

class MyListRestaurantsAdapter(var restaurantes: MutableList<RestauranteEntity>, private var listener: OnClickListener) : RecyclerView.Adapter<MyListRestaurantsAdapter.ViewHolder>() {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_my_restaurants, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val restaurante = restaurantes[position]

        with(holder) {

            setListener(restaurante)

            with(bindingRestaurants) {

                rvMyEventsNombreRestaurante.text = restaurante.nombre

            }

            Glide.with(fragmentContext)
                .load(restaurante.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(bindingRestaurants.imgMyRestaurants)

        }

    }

    override fun getItemCount(): Int = restaurantes.size

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(filteredList: MutableList<RestauranteEntity>) {

        restaurantes = filteredList

        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setRestaurante(restaurantes: MutableList<RestauranteEntity>) {

        this.restaurantes = restaurantes

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val bindingRestaurants = ItemMyRestaurantsBinding.bind(view)

        fun setListener(restauranteEntity: RestauranteEntity) {

            bindingRestaurants.root.setOnClickListener {

                listener.onClickRestaurante(restauranteEntity)

            }

        }

    }

}