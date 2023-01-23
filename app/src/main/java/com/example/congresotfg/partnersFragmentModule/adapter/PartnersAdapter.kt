package com.example.congresotfg.partnersFragmentModule.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.congresotfg.common.entities.EventoEntity
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.utils.OnClickListener
import com.example.congresotfg.databinding.ItemAsistente1Binding
import com.example.congresotfg.databinding.ItemRecentEventsBinding
import com.example.congresotfg.partnersFragmentModule.SocioListener

class PartnersAdapter(private var socios: MutableList<SocioEntity>, private var listener: SocioListener): RecyclerView.Adapter<PartnersAdapter.ViewHolder>() {

    private lateinit var fragmentContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        fragmentContext = parent.context

        val view = LayoutInflater.from(fragmentContext).inflate(R.layout.item_asistente1, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val socio = socios[position]

        with(holder) {

            setListener(socio)

            with(binding) {
                nombreUsuario.text=socio.asistente.nombreUsuario
                empresa.text=socio.empresa.nombre
                cargoEmpresa.text=socio.cargo

            }

            Glide.with(fragmentContext)
                .load(socio.asistente.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imagenAsistente)

        }

    }

    override fun getItemCount(): Int = socios.size

    fun setSocio(socios: MutableList<SocioEntity>) {

        this.socios = socios

        notifyDataSetChanged()

    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemAsistente1Binding.bind(view)

        fun setListener(socioEntity: SocioEntity) {

            with(binding) {

                root.setOnClickListener {
                    clEmpresa.setOnClickListener(){
                        listener.onClickEmpresa(socioEntity.empresa)
                    }
                }

            }

        }

    }

}