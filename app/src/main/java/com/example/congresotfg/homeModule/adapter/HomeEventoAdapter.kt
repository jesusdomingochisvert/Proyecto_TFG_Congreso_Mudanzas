package com.example.congresotfg.homeModule.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.R
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.ItemRecentEventsBinding

class HomeEventoAdapter(private var eventos: MutableList<EventoEntity>, private var listener: OnClickListener): RecyclerView.Adapter<HomeEventoAdapter.ViewHolder>() {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_recent_events, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val evento = eventos.get(position)

        with(holder) {

            setListener(evento)

            with(binding) {

                rvRecentEventsNombreEvento.text = evento.nombre
                rvRecentEventsHoraInicio.text = evento.hora_inicio

            }

            Glide.with(fragmentContext)
                .load(evento.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgRecentEvents)

        }

    }

    override fun getItemCount(): Int = eventos.size

    fun setEvento(eventos: MutableList<EventoEntity>) {

        this.eventos = eventos

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemRecentEventsBinding.bind(view)

        fun setListener(eventoEntity: EventoEntity) {

            with(binding) {

                root.setOnClickListener {

                    listener.onClickEvento(eventoEntity)

                }

            }

        }

    }

}