package com.example.congresotfg.eventoInfoModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.PonenteEntity
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.AsistenteListener
import com.example.congresotfg.databinding.ItemAsistente2Binding

class AsistentesListAdapter(private var listener: AsistenteListener): ListAdapter<AsistenteEntity,RecyclerView.ViewHolder>(
    AsistenteDiffCallback()
) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_asistente2, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val asistente = getItem(position)

        with(holder as ViewHolder) {

            setListener(asistente)

            with(binding) {
                nombreUsuario.text=asistente.nombreUsuario
                ImageClass().loadImage(asistente.imagen,imagenAsistente,fragmentContext)

            }
        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemAsistente2Binding.bind(view)

        fun setListener(asistenteEntity: AsistenteEntity) {

            with(binding) {
                root.setOnClickListener {
                    listener.onClickAsistente(asistenteEntity)
                }
            }

        }

    }

}

class AsistenteDiffCallback:DiffUtil.ItemCallback<AsistenteEntity>(){


    override fun areItemsTheSame(oldItem: AsistenteEntity, newItem: AsistenteEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AsistenteEntity, newItem: AsistenteEntity): Boolean {
        return oldItem == newItem
    }

}
