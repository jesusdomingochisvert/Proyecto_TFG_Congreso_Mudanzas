package com.example.congresotfg

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.congresotfg.databinding.FragmentContactoBinding

class ContactoFragment : Fragment() {

    private lateinit var binding: FragmentContactoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentContactoBinding.inflate(inflater, container, false)

        sendEmail()

        binding.fabCall.setOnClickListener {

            dial()

        }

        return binding.root

    }

    private fun sendEmail() {

        with(binding) {

            btnSendEmail.setOnClickListener {

                val correo = tvEmailContact.text.toString()
                val reason = tietReason.text.toString()
                val msg = tietMessage.text.toString()

                val intent = Intent(Intent.ACTION_SEND)

                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))
                intent.putExtra(Intent.EXTRA_SUBJECT, reason)
                intent.putExtra(Intent.EXTRA_TEXT, msg)

                intent.setType("message/rfc822")

                startActivity(Intent.createChooser(intent, "Elige un cliente de Correo:"))

            }

        }

    }

    private fun dial() {

        val phone = binding.tvNumberContact.text.toString()

        val callIntent = Intent().apply {

            action = Intent.ACTION_DIAL

            data = Uri.parse("tel:$phone")

        }

        startActivity(callIntent)

    }

}