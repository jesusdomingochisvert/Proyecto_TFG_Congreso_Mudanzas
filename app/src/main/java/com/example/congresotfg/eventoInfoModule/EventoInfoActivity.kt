package com.example.congresotfg.eventoInfoModule

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.congresotfg.CongresoApplication
import com.example.congresotfg.R
import com.example.congresotfg.common.entities.AsistenteEntity
import com.example.congresotfg.common.entities.SocioEntity
import com.example.congresotfg.common.retrofit.metodos.EventoMethods
import com.example.congresotfg.common.utils.CorrutinaClass
import com.example.congresotfg.common.utils.ImageClass
import com.example.congresotfg.common.utils.listeners.AsistenteListener
import com.example.congresotfg.common.utils.listeners.SocioListener
import com.example.congresotfg.databinding.ActivityEventoDialogBinding
import com.example.congresotfg.eventoInfoModule.adapter.AsistentesListAdapter
import com.example.congresotfg.eventoInfoModule.adapter.PonentesListAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*

class EventoInfoActivity : AppCompatActivity(), SocioListener,AsistenteListener {

    private lateinit var binding: ActivityEventoDialogBinding

    private lateinit var fragmentContext: Context

    private lateinit var asistentesAdapter: AsistentesListAdapter

    private lateinit var ponentesAdapter: PonentesListAdapter

    private lateinit var linearLayoutManager: RecyclerView.LayoutManager

    private var valoracion = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventoDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentContext = this

        CongresoApplication.idEvento = intent.extras?.getLong("id_evento")

        comprobar()

        getEvento()
        getPonentes()
        getAsistentes()
        setupRecyclerViewAsistentes()
        setupRecyclerViewPonentes()

        binding.btnAsistir.setOnClickListener {

            asistir()

        }

        binding.btnBack.setOnClickListener(){
            onBackPressed()
        }
    }

    private fun asistir() {

        try {

            showDialog()
        } catch (e: Exception) {

            Log.i("error", e.message.toString())

        }

    }

    private fun comprobar(){

        try {

            CorrutinaClass().executeAction(fragmentContext){

                val valoracion = EventoMethods().getValoracion()

                if (valoracion?.evento?.id != null){

                    binding.btnAsistir.visibility = GONE
                    binding.txtValorado.visibility=VISIBLE

                }

            }

        }catch (e:Exception){

            Log.i("ERROR_RESERVA", e.message.toString())

        }

    }

    private fun showDialog() {

        val popDialog = AlertDialog.Builder(this)

        val linearLayout = LinearLayout(this)

        val rating = RatingBar(this)

        val lp = LinearLayout.LayoutParams(

            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT

        )

        rating.layoutParams = lp

        rating.numStars = 5

        rating.stepSize = 0.5F

        linearLayout.addView(rating)

        popDialog.setTitle("Vota")

        popDialog.setView(linearLayout)

        popDialog.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, _ ->

            CorrutinaClass().executeAction(fragmentContext) {

                EventoMethods().postValoracion(CongresoApplication.idEvento!!, rating.rating)
                binding.btnAsistir.visibility = GONE
                binding.txtValorado.visibility=VISIBLE
            }

            dialog.dismiss()

        }).setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, _ ->

            dialog.cancel()

        })

        popDialog.create()

        popDialog.show()

    }

    private fun setupRecyclerViewAsistentes() {

        asistentesAdapter = AsistentesListAdapter(this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.asistentesRecycler.apply {

            layoutManager = linearLayoutManager

            adapter = asistentesAdapter

        }
    }

    private fun setupRecyclerViewPonentes() {

        ponentesAdapter = PonentesListAdapter(this)

        linearLayoutManager = LinearLayoutManager(fragmentContext, LinearLayoutManager.HORIZONTAL, false)

        binding.ponentesRecycler.apply {

            layoutManager = linearLayoutManager

            adapter = ponentesAdapter

        }
    }

    @SuppressLint("ResourceAsColor")
    private fun getEvento(){

        CorrutinaClass().executeAction(fragmentContext) {
            val eventoEntity = EventoMethods().getEvento(CongresoApplication.idEvento)
            with(binding){
                tituloEvento.text = eventoEntity.nombre.toUpperCase()
                ImageClass().loadImage(eventoEntity.imagen,imgEvento,fragmentContext)
                lugarEvento.text=eventoEntity.lugar
                hora.text = buildString {
                    append(eventoEntity.horaInicio)
                    append("-")
                    append(eventoEntity.horaFin)
                }
                val valoracion = EventoMethods().getValoracionMedia(CongresoApplication.idEvento)
                descripcionTxt.text= eventoEntity.descripcion
                rBar.visibility=VISIBLE
                containerDialogEvento.visibility=VISIBLE
                imgEvento.visibility=VISIBLE
                binding.rBar.rating = valoracion!!.replace(",",".").toFloat()
                progressbar.visibility=GONE
            }  }

    }
    private fun getPonentes(){
        val id = intent.extras?.getLong("id_evento")

        CorrutinaClass().executeAction(fragmentContext) {
            val ponentes = EventoMethods().getPonentes(id)
            ponentesAdapter.submitList(ponentes)
        }
    }

    private fun getAsistentes(){
        val id = intent.extras?.getLong("id_evento")

        CorrutinaClass().executeAction(fragmentContext) {
            val asistentes = EventoMethods().getAsistentes(id)
            asistentesAdapter.submitList(asistentes)
        }
    }

    override fun onClickSocio(socioEntity: SocioEntity) {
        val items = arrayOf("Ir a la web de la empresa", "Contactar")
        MaterialAlertDialogBuilder(this)
            .setItems(items) { _, i ->
                when (i) {
                    1 -> abrirCorreo(socioEntity.asistente.correo)
                    0 -> abrirWeb(socioEntity.empresa.enlace)
                }
            }
            .show()
    }

    override fun onClickAsistente(asistenteEntity: AsistenteEntity) {
        val items = arrayOf("Contactar")
        MaterialAlertDialogBuilder(this)
            .setItems(items) { _, i ->
                when (i) {
                    0 -> abrirCorreo(asistenteEntity.correo)
                }
            }
            .show()
    }

    private fun abrirCorreo(correo: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))
        intent.setType("message/rfc822")
        startActivity(Intent.createChooser(intent, "Elige un cliente de Correo:"))
    }

    private fun abrirWeb(enlace: String) {
        if (enlace.isEmpty()) {
            Toast.makeText(this, R.string.main_error_no_website, Toast.LENGTH_LONG).show()
        } else {
            val websiteIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(enlace)
            }
            startActivity(websiteIntent)
        }
    }
}