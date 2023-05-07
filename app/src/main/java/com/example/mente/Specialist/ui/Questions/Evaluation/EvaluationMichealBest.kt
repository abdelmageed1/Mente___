package com.example.mente.Specialist.ui.Questions.Evaluation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Student.Model.Student
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.constant
import com.example.mente.databinding.FragmentEvaluationMichealBestBinding
import java.text.SimpleDateFormat
import java.util.*

class EvaluationMichealBest : Fragment() {

    lateinit var binding: FragmentEvaluationMichealBestBinding
    lateinit var studentViewModel: StudentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        setActionBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEvaluationMichealBestBinding.inflate(inflater,container,false)

        binding.tvEvaMichTotalPoint.text = totalPoint.toString()
        binding.tvEvaMichTest1.text = testOne.toString()
//            "(1) الاستيعاب : ${String.format("%.2f", testOne )}"
        binding.tvEvaMichTest2.text = testTwo.toString()
//            "(2) التناسق الحركة : ${String.format("%.2f", testTwo)}"
        binding.tvEvaMichTest3.text = testThree.toString()
//            "(3) المعرفة العامة  : ${ String.format("%.2f", testThree )}"
        binding.tvEvaMichTest4.text = testFour.toString()
//            "(4) اللغة : ${ String.format("%.2f", testFour )}"
        binding.tvEvaMichTest5.text = testFive.toString()
//            "(5) الاجتماعي والشخصي : ${String.format("%.2f", testFive )}"

        binding.tvEvaMichTotalCategory1.text = totalCategory1.toString()
//            "اللفظي : ${String.format("%.2f", totalCategory1 )}"
        binding.tvEvaMichTotalCategory2.text = totalCategory2.toString()
//            "غير اللفظي :  ${String.format("%.2f", totalCategory2 )}"


        binding.tvEvaMichTotalPoint.text = totalPoint.toString()
//            "الدرجة الكلية على الاختبار :  ${String.format("%.2f", totalPoint )}"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnAddValueToStudent.setOnClickListener {
            addValueToStudent(constant.MichaelBestCategoryList[0] ,  String.format("%.2f", testOne ))
            addValueToStudent(constant.MichaelBestCategoryList[1] ,  String.format("%.2f", testTwo))
            addValueToStudent(constant.MichaelBestCategoryList[2] , String.format("%.2f", testThree ))
            addValueToStudent(constant.MichaelBestCategoryList[3] ,  String.format("%.2f", testFour ) )
            addValueToStudent(constant.MichaelBestCategoryList[4] ,  String.format("%.2f", testFive ) )
        }




        binding.btnGoToHome.setOnClickListener {
            findNavController().navigate(R.id.action_evaluationMichealBest_to_homeFragment)
        }

        setDegree()
    }











    private fun addValueToStudent(testName:String, valueOfQuiz: String) {

             var quiz = Quiz(testName,constant.quizTypeSpeMichaelBest,valueOfQuiz.toString(),valueOfQuiz.toString(),getCurrentDate(),getCurrentTime())
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






    private fun setDegree(){
        var strDegree = ""
        strDegree = if (totalPoint <= 1.98 ){
            strDegree.plus("- من خلال الدرجة الكلية يوجد صعوبات تعلم لدي هذا الطفل\n")
        } else strDegree.plus(" - من خلال الدرجة الكلية لا يوجد صعوبات تعلم لدي هذا الطفل\n")



        strDegree = if (totalCategory1 <= 1.841  )
            strDegree.plus("- يوجود صعوبات تعلم  فى الإختبار اللفظي\n")
        else strDegree.plus("- لا يوجود صعوبات تعلم  فى الإختبار اللفظي\n")


        strDegree = if (totalCategory2 <= 2.144   )
            strDegree.plus("- يوجود صعوبات تعلم فى الإختبار غير اللفظي\n")
        else strDegree.plus("- لا يوجود صعوبات تعلم فى الإختبار غير اللفظي\n")


        binding.tvDegree.text = strDegree


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

        var totalPoint =0.0f
        var testOne =0.0f
       var  testTwo =0.0f
        var testThree =0.0f
        var testFour =0.0f
        var testFive =0.0f
        var totalCategory1 =0.0f
        var totalCategory2 =0.0f



    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "التقييم "

    }
}