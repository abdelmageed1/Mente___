package com.example.mente.Specialist.ui.Questions.Evaluation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.Degree.DegreeOfIiioni.DegreeOfIiioni
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Student.Model.Student
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.constant
import com.example.mente.databinding.FragmentEvaluationSpeNeuralBinding
import java.text.SimpleDateFormat
import java.util.*


class EvaluationSpeNeuralFragment : Fragment() {

    lateinit var binding: FragmentEvaluationSpeNeuralBinding
    var quizCategory = ""
    lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvaluationSpeNeuralBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()


        setViews()
        setQuizType()
        addValueToStudent()
        gotoHome()

        setDegree()

    }

    private fun setDegree() {

        if (quizCategory == constant.quizTypeSpeIIIIinoi) {
// val almiyiynaaRow = DataDegreeOfFathyElZayat.degreeMapFathyElzayat[scoredValue]
            if (scoredValue <= 47) {

                binding.tvDegreeIIIIinoi.text = setDefineQuizNum()


                // فاضل العمر العقلى يدخل من الاخصائي ثم بناءا ع العمر العقلى هختار الدرجة القياسية ف الداتا ال فادى بيدخلها


            }

        } else {
            binding.tvDegreeText.visibility = View.GONE
            binding.tvDegreeIIIIinoi.visibility = View.GONE
        }
    }

    private fun setDefineQuizNum(): String {
        var psychological_age = when (testName) {
            constant.elIIinoiCategoryList[0] -> DegreeOfIiioni.Psychological_age[scoredValue]?.ar
            constant.elIIinoiCategoryList[1] -> DegreeOfIiioni.Psychological_age[scoredValue]?.vr
            constant.elIIinoiCategoryList[2] -> DegreeOfIiioni.Psychological_age[scoredValue]?.aa
            constant.elIIinoiCategoryList[3] -> DegreeOfIiioni.Psychological_age[scoredValue]?.am
            constant.elIIinoiCategoryList[4] -> DegreeOfIiioni.Psychological_age[scoredValue]?.va
            constant.elIIinoiCategoryList[5] -> DegreeOfIiioni.Psychological_age[scoredValue]?.vc
            constant.elIIinoiCategoryList[6] -> DegreeOfIiioni.Psychological_age[scoredValue]?.gc
            constant.elIIinoiCategoryList[7] -> DegreeOfIiioni.Psychological_age[scoredValue]?.me
            constant.elIIinoiCategoryList[8] -> DegreeOfIiioni.Psychological_age[scoredValue]?.ac
            constant.elIIinoiCategoryList[9] -> DegreeOfIiioni.Psychological_age[scoredValue]?.sb
            constant.elIIinoiCategoryList[10] -> DegreeOfIiioni.Psychological_age[scoredValue]?.ve
            constant.elIIinoiCategoryList[11] -> DegreeOfIiioni.Psychological_age[scoredValue]?.vm

            else -> ""
        }

        return psychological_age!!

    }

    private fun setQuizType() {
        when (testName) {
            in constant.neuralCategoryList -> {
                quizCategory = constant.quizTypeSpeNeural
            }
            in constant.elIIinoiCategoryList -> {
                quizCategory = constant.quizTypeSpeIIIIinoi
            }
            in constant.fathyElZayatCategoryList -> {
                quizCategory = constant.quizTypeSpeFathyElZayat
            }
        }


    }

    @SuppressLint("StringFormatInvalid")
    private fun setViews() {
        //  binding.tvEvaluationTotalPoint.text = totalValue.toString()
        binding.tvEvaluationScoredPoint.text = scoredValue.toString()
//            "  الدرجة الخام  : $scoredValue "
        binding.testNameEvaluation.text = testName.toString()
//            "  اختبار  $testName "
        //   binding.tvNumberOfTotalQuestions.text = "  عدد الاسئلة الكلى  : $numberOfQuestions "
        //    binding.tvNumberOfRightQuestions.text = "   عدد الاجابات الصحيحة : $numberOfRightAns "
        binding.tvEvaluationStudentName.text = currentStudent.name
//            "  الطالب : ${currentStudent.name} "


    }

    private fun gotoHome() {
        binding.btnGoToHome.setOnClickListener {
            findNavController().navigate(R.id.action_evaluationSpeNeuralFragment_to_homeFragment)
        }


    }

    private fun addValueToStudent() {
        binding.btnAddValueToStudent.setOnClickListener {
            var quiz = Quiz(
                testName,
                quizCategory,
                scoredValue.toString(),
                scoredValue.toString(),
                getCurrentDate(),
                getCurrentTime()
            )
            studentViewModel.setQuizValue(quiz, currentStudent)

            studentViewModel.mSetQuizSuccess.observe(viewLifecycleOwner) {
                if (it) {

                    binding.btnAddValueToStudent.isEnabled = false
                }

            }
            studentViewModel.mSetQuizFailure.observe(viewLifecycleOwner) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "    نتيجة الإختبار    "

    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("MMM dd, yyyy")
        return simpleDateFormat.format(calendar.time)
    }

    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("HH:mm:ss ")
        return simpleDateFormat.format(calendar.time)
    }


    companion object {
        lateinit var currentStudent: Student
        var totalValue = 0
        var numberOfQuestions = 0
        var numberOfRightAns = 0
        var scoredValue = 0
        var testName = ""


    }
}