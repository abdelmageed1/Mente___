package com.example.mente.Parent.ui.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mente.Models.Quiz
import com.example.mente.Parent.HomeParentActivity
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationMichealBest
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.constant
import com.example.mente.databinding.FragmentEvaluationParentBinding
import java.text.SimpleDateFormat
import java.util.*


class EvaluationParentFragment : Fragment() {

    lateinit var binding : FragmentEvaluationParentBinding
    lateinit var studentViewModel: StudentViewModel
    private var testEva =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEvaluationParentBinding.inflate(inflater,container,false)


        if(testName == constant.parentTestsCategoryList[0]){
            testEva = when(totalPoint){
                in 0..8 -> "قصور شديد في مهارة الإنتباه"
                in 9..16 -> "قصور متوسط في مهارة الإنتباه"
                else -> "قصور خفيف في مهارة الإنتباه"
            }.toString()
        }

        if(testName == constant.parentTestsCategoryList[1]){
            testEva = when(totalPoint){
                in 0..9 -> "قصور شديد في مهارة الإدراك"
                in 10..18 -> "قصور متوسط في مهارة الإدراك"
                else -> "قصور خفيف في مهارة الإدراك"
            }.toString()
        }


        if(testName == constant.parentTestsCategoryList[2]){
            testEva = when(totalPoint){
                in 0..10 -> "قصور شديد في مهارة الاستدلال وتمثيل المعلومات "
                in 11..22 -> "قصور متوسط في مهارة الاستدلال وتمثيل المعلومات"
                else -> "قصور خفيف في مهارة الاستدلال وتمثيل المعلومات "
            }.toString()
        }


        if(testName == constant.parentTestsCategoryList[3]){
            testEva = when(totalPoint){
                in 0..7 -> "قصور شديد في مهارة  الذاكرة "
                in 8..14 -> "قصور متوسط في مهارة  الذاكرة"
                else -> "قصور خفيف في مهارة الذاكرة"
            }.toString()
        }

        if(testName == constant.parentTestsCategoryList[4]){
            testEva = when(totalPoint){
                in 0..8 -> "قصور شديد في مهارة  التفكير والتخيل"
                in 9..16 -> "قصور متوسط في مهارة  التفكير والتخيل"
                else -> "قصور خفيف في مهارة  التفكير والتخيل "
            }.toString()
        }

        if(testName == constant.parentTestsCategoryList[5]){
            testEva = when(totalPoint){
                in 0..7 -> "قصور شديد في مهارة التقليد"
                in 8..14 -> "قصور متوسط في  مهارة التقليد"
                else -> "قصور خفيف في مهارة التقليد "
            }.toString()
        }





        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.testResult.text = totalPoint.toString()
        binding.testName.text = testName
        binding.evaluationResult.text= testEva

        binding.goToMainPage.setOnClickListener {
            findNavController().navigate(R.id.action_evaluationParentFragment_to_nav_home_parent)
        }
        binding.goToNextTestBtn.setOnClickListener {
            findNavController().navigate(R.id.action_evaluationParentFragment_to_testsFragment)
        }


        binding.childSaveBtn.setOnClickListener {
            addValueToStudent( testName,  String.format("%.2f", totalPoint.toDouble() ))

        }
    }




    private fun addValueToStudent(testName:String, valueOfQuiz: String) {

        var quiz = Quiz(testName,
            "",valueOfQuiz.toString(),valueOfQuiz.toString(),getCurrentDate(),getCurrentTime())
        studentViewModel.setQuizParentValue(quiz)

        studentViewModel.mSetQuizParentSuccess.observe(viewLifecycleOwner) {
            if (it) {

                binding.childSaveBtn.isEnabled = false
            }

        }
        studentViewModel.mSetQuizParentFailure.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

    }










    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat =
            SimpleDateFormat("MMM dd, yyyy")
        return simpleDateFormat.format(calendar.time)
    }

    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss ")
        return simpleDateFormat.format(calendar.time)
    }


    companion object {

        var totalPoint = 0
        var testName =""
    }


}