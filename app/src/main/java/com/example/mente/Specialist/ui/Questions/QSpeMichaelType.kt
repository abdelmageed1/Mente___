package com.example.mente.Specialist.ui.Questions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.QuestionsSpecialist.michaelBest.DataMichaelBest
import com.example.mente.Models.ModelMichealBest
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationMichealBest
import com.example.mente.constant
import com.example.mente.databinding.FragmentQSpeMichaelTypeBinding


class QSpeMichaelType : Fragment() {

    lateinit var binding : FragmentQSpeMichaelTypeBinding
    lateinit var arrQuestion: MutableList<ModelMichealBest>
    private var indexCurrentQuestion = 0
    private var totalPoint = 0

    var testOne =0
    var  testTwo =0
    var testThree =0
    var testFour =0
    var testFive =0
    var totalCategory1 =0
    var totalCategory2 =0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBar()
        arrQuestion = DataMichaelBest.MichaelBestQ

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentQSpeMichaelTypeBinding.inflate(inflater,container,false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setMainTestName()
        setSubTestName()

        // GetArrQuestions()
        setQuestionInView()

        setBtnNext()

    }

    private fun setMainTestName(){

      when(indexCurrentQuestion) {
           in 0..12 ->  binding.tvMichaelMainCategory.text = "الاختبار اللفظي"
          else -> binding.tvMichaelMainCategory.text = "الاختبار غير اللفظي"

       }

    }

    private fun setSubTestName(){

        when(indexCurrentQuestion){
            in 0..3 -> binding.tvMichTeatName.text = constant.MichaelBestCategoryList[0]
            in 4..8 -> binding.tvMichTeatName.text = constant.MichaelBestCategoryList[1]
            in 9..12 ->binding.tvMichTeatName.text = constant.MichaelBestCategoryList[2]
            in 13..15 -> binding.tvMichTeatName.text = constant.MichaelBestCategoryList[3]
            else -> binding.tvMichTeatName.text = constant.MichaelBestCategoryList[4]
        }


    }


    private fun setBtnNext() {
        binding.radioGroupQMicheal.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//            var rbSelected = binding.radioGroupQMicheal.checkedRadioButtonId
            var ans = 0
            var isChecked = true

            when (optionId) {
                R.id.rbMichealSelect1 -> ans = 1
                R.id.rbMichealSelect2 -> ans = 2
                R.id.rbMichealSelect3 -> ans = 3
                R.id.rbMichealSelect4 -> ans = 4
                R.id.rbMichealSelect5 -> ans = 5
                else -> isChecked = false
            }

            if (isChecked) {
                totalPoint += ans

                setDegree(ans)

                indexCurrentQuestion++


                if (indexCurrentQuestion < arrQuestion.size) {
                    // go to next question
                    setQuestionInView()


                }
                else
                {
                    findNavController().navigate(R.id.action_QSpeMichaelType_to_evaluationMichealBest)
                    EvaluationMichealBest.totalPoint = totalPoint/24f
                    EvaluationMichealBest.testOne = testOne/4f
                    EvaluationMichealBest.testTwo = testTwo/5f
                    EvaluationMichealBest.testThree = testThree/4f
                    EvaluationMichealBest.testFour = testFour/3f
                    EvaluationMichealBest.testFive = testFive/8f

                    EvaluationMichealBest.totalCategory1 = totalCategory1/13f
                    EvaluationMichealBest.totalCategory2 = totalCategory2/11f



                }
            }

//            else {
//                Toast.makeText(context, "اختر الاجابات", Toast.LENGTH_SHORT).show()
//
//            }

            setMainTestName()
            setSubTestName()

        }
    }
    }
    private fun setDegree(ans: Int) {
      when(indexCurrentQuestion)
      {
          in 0..3 ->{totalCategory1 += ans ; testOne+=ans }
          in 4..8 ->{totalCategory1 += ans ; testTwo+=ans}
          in 9..12 ->{totalCategory1 += ans ; testThree+=ans}
          in 13..15 ->{ totalCategory2 += ans ; testFour+=ans}
          in 16..23 ->{totalCategory2 += ans ; testFive+=ans}
      }
    }

    private fun setQuestionInView() {
        if (indexCurrentQuestion < arrQuestion.size)
        {
            binding.tvQTitleMicheal.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.rbMichealSelect1.text = arrQuestion[indexCurrentQuestion].answer1
            binding.rbMichealSelect2.text = arrQuestion[indexCurrentQuestion].answer2
            binding.rbMichealSelect3.text = arrQuestion[indexCurrentQuestion].answer3
            binding.rbMichealSelect4.text = arrQuestion[indexCurrentQuestion].answer4
            binding.rbMichealSelect5.text = arrQuestion[indexCurrentQuestion].answer5

            binding.radioGroupQMicheal.clearCheck()

        }
    }


    companion object {
        lateinit var typeBtnQuestions: String

    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "الإختبارات "

    }

}