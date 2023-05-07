package com.example.mente.Parent.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mente.Parent.ui.screens.TestTypeParent
import com.example.mente.R
import com.example.mente.constant
import com.example.mente.databinding.FragmentHomeParentBinding

class HomeParentFragment : Fragment() {
    lateinit var binding: FragmentHomeParentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeParentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.TestsBtn.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_parent_to_testsFragment)

        }

    binding.instructionBtn.setOnClickListener {
            findNavController().navigate(R.id.action_nav_home_parent_to_instructionFragment)

        }


    }


}