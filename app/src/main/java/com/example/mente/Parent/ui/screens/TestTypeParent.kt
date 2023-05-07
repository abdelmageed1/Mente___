package com.example.mente.Parent.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mente.Models.ParentTestModel
import com.example.mente.Parent.HomeParentActivity
import com.example.mente.Data.QuestionsParent.data.DataParentTests
import com.example.mente.R
import com.example.mente.constant
import com.example.mente.databinding.FragmentTestTypeParentBinding


class TestTypeParent : Fragment() {

    lateinit var binding: FragmentTestTypeParentBinding
    lateinit var arrQuestion: MutableList<ParentTestModel>
    private var indexCurrentQuestion = 0
    private var totalPoint = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setActionBar()

        when (typeBtnQuestions) {

            constant.parentTestsCategoryList[0] -> {
                arrQuestion = DataParentTests.ParentTestOne
            }

            constant.parentTestsCategoryList[1] -> {
                arrQuestion = DataParentTests.ParentTestTwo
            }

            constant.parentTestsCategoryList[2] -> {
                arrQuestion = DataParentTests.ParentTestThree
            }

            constant.parentTestsCategoryList[3] -> {
                arrQuestion = DataParentTests.ParentTestFour
            }

            constant.parentTestsCategoryList[4] -> {
                arrQuestion = DataParentTests.ParentTestFive
            }

            constant.parentTestsCategoryList[5] -> {
                arrQuestion = DataParentTests.ParentTestSix
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTestTypeParentBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setQuestionInView()
        setBtnNext()
    }


    private fun setBtnNext() {

        binding.radioGroupQParent.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//                var rbSelected = binding.radioGroupQParent.checkedRadioButtonId
                var ans = 0
                var isChecked = true

                when (optionId) {
                    R.id.rbParentSelect1 -> ans = 1
                    R.id.rbParentSelect2 -> ans = 0
                    else -> isChecked = false
                }

                if (isChecked) {
                    totalPoint += ans
                    indexCurrentQuestion++

                    if (indexCurrentQuestion < arrQuestion.size) {
                        // go to next question
                        setQuestionInView()

                    } else {
                        findNavController().navigate(R.id.action_testTypeParent_to_evaluationParentFragment)
                        EvaluationParentFragment.totalPoint = totalPoint
                        EvaluationParentFragment.testName = typeBtnQuestions

                    }
                }
//                else {
//                    Toast.makeText(context, "اختر الاجابات", Toast.LENGTH_SHORT).show()
//
//                }

            }
        }
    }

    private fun setQuestionInView() {
        if (indexCurrentQuestion < arrQuestion.size) {
            binding.tvQParentTest.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.radioGroupQParent.clearCheck()

        }
    }


    companion object {
        lateinit var typeBtnQuestions: String

    }


//    private fun setActionBar() {
//        var act = activity as HomeParentActivity
//        act.supportActionBar?.title = "الاختبارات "
//
//    }
}