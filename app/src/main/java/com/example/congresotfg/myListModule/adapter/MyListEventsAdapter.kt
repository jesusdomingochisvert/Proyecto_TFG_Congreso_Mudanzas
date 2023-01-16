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
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.ItemMyEventsBinding

class MyListEventsAdapter(var eventos: MutableList<EventoEntity>, private var listener: OnClickListener) : RecyclerView.Adapter<MyListEventsAdapter.ViewHolder>() {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_my_events, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val evento = eventos.get(position)

        with(holder) {

            setListenerEvents(evento)

            with(bindingEvents) {

                rvMyEventsNombreEvento.text = evento.nombre
                rvMyEventsHoraInicio.text = evento.hora_inicio

            }

            Glide.with(fragmentContext)
                .load(evento.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(bindingEvents.imgMyEvents)

        }

    }

    override fun getItemCount(): Int = eventos.size

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredList(filteredList: MutableList<EventoEntity>) {

        eventos = filteredList

        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEvento(eventos: MutableList<EventoEntity>) {

        this.eventos = eventos

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val bindingEvents = ItemMyEventsBinding.bind(view)

        fun setListenerEvents(eventoEntity: EventoEntity) {

            bindingEvents.root.setOnClickListener {

                listener.onClickEvento(eventoEntity)

            }

        }

    }

}