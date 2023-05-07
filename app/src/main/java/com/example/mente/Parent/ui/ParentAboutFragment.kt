package com.example.mente.Parent.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.databinding.FragmentAboutParentBinding


class ParentAboutFragment : Fragment() {

    lateinit var binding: FragmentAboutParentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAboutParentBinding.inflate(inflater ,container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }





}