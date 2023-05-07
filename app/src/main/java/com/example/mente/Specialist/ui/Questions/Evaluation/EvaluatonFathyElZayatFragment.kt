package com.example.mente.Specialist.ui.Questions.Evaluation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.Degree.DegreeOfFathyElZayat.DataDegreeOfFathyElZayat
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Student.Model.Student
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.constant
import com.example.mente.databinding.FragmentEvaluatonFathyElZayatBinding
import java.text.SimpleDateFormat
import java.util.*


class EvaluatonFathyElZayatFragment : Fragment() {
    lateinit var binding :FragmentEvaluatonFathyElZayatBinding
    lateinit var studentViewModel: StudentViewModel
    var quizCategory = ""
    var valueOfQuiz =""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEvaluatonFathyElZayatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        setQuizType()
        setViews()
        addValueToStudent()
        gotoHome()

        setDegreeOfMeanAndAlmiyiynaa()

    }

    private fun setDegreeOfMeanAndAlmiyiynaa() {

        var index = constant.fathyElZayatCategoryList.indexOf(testName)
        if (index in 0..7){

            val mean = DataDegreeOfFathyElZayat.meanOfFathyElzayat[index]
            val almiyiynaaRow = DataDegreeOfFathyElZayat.degreeMapFathyElzayat[scoredValue]
            val almiyiynaa = when(index){
                0 -> almiyiynaaRow?.fathyLIst1
                1-> almiyiynaaRow?.fathyLIst2
                2-> almiyiynaaRow?.fathyLIst3
                3-> almiyiynaaRow?.fathyLIst4
                4-> almiyiynaaRow?.fathyLIst5
                5-> almiyiynaaRow?.fathyLIst6
                6-> almiyiynaaRow?.fathyLIst7
                7-> almiyiynaaRow?.fathyLIst8

                else -> {0}
            }

            binding.tvDegreeOfMeanAlmiyiynaa.text= "الدرجة الخام  = $scoredValue  \n المتوسط = $mean \n الميئينى =  $almiyiynaa  "
        }

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

    private fun setViews() {

        binding.testNameEvaluationFathyElZayat.text = testName
//            "  اختبار  $testName "
        // binding.tvNumberOfTotalQuestions.text = "  عدد الاسئلة الكلى  : ${EvaluationSpeNeuralFragment.numberOfQuestions} "
        //binding.tvNumberOfRightQuestions.text = "   عدد الاجابات الصحيحة : ${EvaluationSpeNeuralFragment.numberOfRightAns} "
        binding.tvStudentNameEvaluationFathyElzayat.text = currentStudent.name
//            "  الطالب : ${currentStudent.name} "

        valueOfQuiz =
            when(scoredValue){
                in 0..20 -> " لا صعوبات "
                in 21..40 -> " صعوبات خفيفية "
                in 41..60 -> "صعوبات متوسطة  "
                else -> "صعوبات شديدة"
            }
        binding.tvResultEvaluationFathyElzayat.text = "الطالب يعانى من : $valueOfQuiz "


    }
    private fun gotoHome() {
        binding.btnGoToHome.setOnClickListener {
            findNavController().navigate(R.id.action_evaluatonFathyElZayatFragment3_to_homeFragment)
        }
    }



    private fun addValueToStudent() {
        binding.btnAddValueToStudent.setOnClickListener {
            var quiz = Quiz(testName ,quizCategory,valueOfQuiz,valueOfQuiz,getCurrentDate(),getCurrentTime())
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
        act.supportActionBar?.title = "    نتيجة الاختبار    "

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
        lateinit var currentStudent: Student
        var totalValue = 0
        var numberOfQuestions = 0
        var numberOfRightAns = 0
        var scoredValue = 0
        var testName = ""
    }
}