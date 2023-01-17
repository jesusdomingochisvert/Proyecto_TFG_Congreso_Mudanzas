package com.example.congresotfg.aboutModule

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.congresotfg.databinding.FragmentAboutBinding
import com.example.congresotfg.databinding.FragmentContactoBinding

class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentAboutBinding.inflate(inflater, container, false)

        return binding.root

        //HOLA 2

        //OFHSTIASH
        

    }

}