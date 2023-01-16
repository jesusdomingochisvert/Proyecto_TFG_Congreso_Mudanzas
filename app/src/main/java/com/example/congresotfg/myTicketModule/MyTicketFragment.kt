package com.example.congresotfg.myTicketModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.congresotfg.databinding.FragmentAboutBinding
import com.example.congresotfg.databinding.FragmentMyTicketBinding

class MyTicketFragment : Fragment() {

    private lateinit var binding: FragmentMyTicketBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentMyTicketBinding.inflate(inflater, container, false)

        return binding.root

    }

}