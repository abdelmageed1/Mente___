package com.example.mente.Parent.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mente.Parent.HomeParentActivity
import com.example.mente.R
import com.example.mente.constant
import com.example.mente.databinding.FragmentTestsBinding


class TestsFragment : Fragment() {

    lateinit var binding : FragmentTestsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setActionBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.parentTest1Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[0]
        }

        binding.parentTest2Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[1]
        }


        binding.parentTest3Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[2]
        }

        binding.parentTest4Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[3]
        }

        binding.parentTest5Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[4]
        }

        binding.parentTest6Btn.setOnClickListener {
            findNavController().navigate(R.id.action_testsFragment_to_testTypeParent)
            TestTypeParent.typeBtnQuestions = constant.parentTestsCategoryList[5]
        }

    }

//    private fun setActionBar() {
//        var act = activity as HomeParentActivity
//        act.supportActionBar?.title = "الاختبارات "
//
//    }

}