package com.example.mente.Parent.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.Authentication.Ui.Login
import com.example.mente.R
import com.example.mente.databinding.FragmentParentSettingBinding

class ParentSettingFragment : Fragment() {
    lateinit var binding: FragmentParentSettingBinding
    private lateinit var authViewModel: AuthVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthVM::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentParentSettingBinding.inflate(inflater,container ,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        signOut()


    }


    private fun signOut() {

        binding.btnSingOut.setOnClickListener {
            authViewModel.signOut()
            startActivity(Intent(context, Login::class.java))
            activity?.finish()
        }
    }



    }
