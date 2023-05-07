package com.example.mente.Specialist.ui.menuDrawer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.Authentication.Ui.Login
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.databinding.FragmentSettingBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SettingFragment : Fragment() {
     lateinit var binding :FragmentSettingBinding
    private lateinit var authViewModel: AuthVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authViewModel = ViewModelProvider(this)[AuthVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSettingBinding.inflate(inflater ,container,false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()

//      signOut()




   }



    private fun signOut() {

        binding.btnSingOut.setOnClickListener {
           // AuthRepo.getInstance().signOut()

            startActivity(Intent(context, Login::class.java))
            activity?.finish()
            Firebase.auth.signOut()
        }
    }


    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "Setting "
    }
}