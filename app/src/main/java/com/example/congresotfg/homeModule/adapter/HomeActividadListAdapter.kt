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
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.ActividadEntity
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.ActividadListener
import com.example.congresotfg.databinding.ItemActividadesBinding

import com.example.congresotfg.databinding.ItemRecentEventsBinding

class HomeActividadListAdapter(private var listener: ActividadListener):
    ListAdapter<ActividadEntity, RecyclerView.ViewHolder>(ActividadDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_actividades, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val activivdad = getItem(position)

        with(holder as ViewHolder) {

            setListener(activivdad)

            with(binding) {

                nombreActividad.text = activivdad.nombre
                horaActividad.text = activivdad.horaInicio

                ImageClass().loadImage(activivdad.imagen,imgActividad,fragmentContext)

            }

        }

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemActividadesBinding.bind(view)

        fun setListener(actividadEntity: ActividadEntity) {

            with(binding) {

                root.setOnClickListener {
                    listener.onClickActividad(actividadEntity)
                }

            }

        }

    }

    class ActividadDiffCallback: DiffUtil.ItemCallback<ActividadEntity>() {

        override fun areItemsTheSame(oldItem: ActividadEntity, newItem: ActividadEntity): Boolean {

            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: ActividadEntity, newItem: ActividadEntity): Boolean {

            return oldItem == newItem

        }


    }

}