package com.example.congresotfg.allEventsModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.retrofit.metodos.EventoMethods
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.EventoListener
import com.example.congresotfg.databinding.ItemMyEventsBinding

class AllEventsListAdapter(private var listener: EventoListener):
    ListAdapter<EventoEntity, RecyclerView.ViewHolder>(EventoDiffCallback()) {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_my_events, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val evento = getItem(position)

        with(holder as ViewHolder) {
            setListener(evento)
            CorrutinaClass().executeAction(fragmentContext) {
                val valoracion = EventoMethods().getValoracionMedia(evento.id)

                with(binding) {
                    valoraciontxt.text=valoracion.toString()
                    nombreEvento.text = evento.nombre.toUpperCase()
                    horaInicio.text = evento.horaInicio
                    ImageClass().loadImage(evento.imagen,img,fragmentContext)

                }
            }
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemMyEventsBinding.bind(view)

        fun setListener(eventoEntity: EventoEntity) {
            with(binding) {

                root.setOnClickListener {
                    listener.onClickEvento(eventoEntity)
                }

            }

        }

    }

    class EventoDiffCallback: DiffUtil.ItemCallback<EventoEntity>() {
        override fun areItemsTheSame(oldItem: EventoEntity, newItem: EventoEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventoEntity, newItem: EventoEntity): Boolean {
            return oldItem == newItem
        }


    }

}