package com.example.mente.Specialist.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.databinding.FragmentAboutUsSpecialistBinding


class AboutUsFragment : Fragment() {
   lateinit var binding :FragmentAboutUsSpecialistBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding =FragmentAboutUsSpecialistBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBar()


    }

    private fun setActionBar() {
       var act = activity as HomeSpecialistActivity
       act.supportActionBar?.title = "About us Page "

    }


}