package com.example.congresotfg.eventoInfoModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.PonenteEntity
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.databinding.ItemAsistente2Binding
import com.example.congresotfg.common.utils.listeners.SocioListener

class PonentesListAdapter(private var listener: SocioListener): ListAdapter<PonenteEntity,RecyclerView.ViewHolder>(
    PonenteDiffCallback()
) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_asistente2, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val ponente = getItem(position)

        with(holder as ViewHolder) {

            setListener(ponente)

            with(binding) {
                nombreUsuario.text=ponente.socio.asistente.nombreUsuario
                ImageClass().loadImage(ponente.socio.asistente.imagen,imagenAsistente,fragmentContext)

            }
        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemAsistente2Binding.bind(view)

        fun setListener(ponenteEntity: PonenteEntity) {

            with(binding) {
                root.setOnClickListener {
                    listener.onClickSocio(ponenteEntity.socio)
                    true
                }
            }

        }

    }

}

class PonenteDiffCallback:DiffUtil.ItemCallback<PonenteEntity>(){

    override fun areItemsTheSame(oldItem: PonenteEntity, newItem: PonenteEntity): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: PonenteEntity, newItem: PonenteEntity): Boolean {
        return oldItem == newItem
    }

}
