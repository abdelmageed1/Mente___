package com.example.mente.Specialist.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mente.Authentication.AuthenticationViewModel.AuthVM
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.QSpeMichaelType
import com.example.mente.Specialist.ui.Questions.QSpeTypeFragment
import com.example.mente.Student.View.AddStudentFragment
import com.example.mente.constant
import com.example.mente.databinding.FragmentHomeSpecialistBinding

class HomeSpecialistFragment : Fragment() {

    private lateinit var binding: FragmentHomeSpecialistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //   val homeSpecialistViewModel = ViewModelProvider(this)[HomeSpecialistViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeSpecialistBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.btnQNeural.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_QSpeTypeFragment)
            QSpeTypeFragment.typeBtnQuestions = constant.btnGoToNeuralQuestions
        }


        binding.btnElUnoi.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_QSpeTypeFragment)
            QSpeTypeFragment.typeBtnQuestions = constant.btnGoToElioniQuestions
        }


        binding.btnFathyElZayat.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_QSpeTypeFragment)
            QSpeTypeFragment.typeBtnQuestions = constant.btnGoToFathyElZayatQuestions
        }

        binding.btnMichael.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addStudentFragment)
            QSpeMichaelType.typeBtnQuestions = constant.btnGoToMichaelBestQuestions
            AddStudentFragment.testName = constant.MichaelBestCategoryList[0]

        }



    }


//    private fun setActionBar() {
//        var act = activity as HomeSpecialistActivity
//        act.supportActionBar?.title = "الصفحة الريسية "
//
//    }





}