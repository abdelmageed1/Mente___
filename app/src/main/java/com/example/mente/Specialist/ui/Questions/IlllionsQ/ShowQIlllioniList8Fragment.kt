package com.example.mente.Specialist.ui.Questions.IlllionsQ

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.QuestionsSpecialist.neuralIllinois.DataNeuralIllinois
import com.example.mente.Models.Question
import com.example.mente.R
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList3Binding
import com.example.mente.databinding.FragmentShowQIlllioniList8Binding

class ShowQIlllioniList8Fragment : Fragment() {
    lateinit var binding: FragmentShowQIlllioniList8Binding
    private var showedAns = false
    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
    private var ans: Int = 0
    private var totalPoint = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arrQuestion = DataNeuralIllinois.IllinoisDataList8


    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList8Binding.inflate(inflater,container,false)
        return binding.root}



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setQuestionInView()
        showAns()
        setBtnNext()
    }
    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//                var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
                ans = 0

                var isChecked = true

                when (optionId) {
                    R.id.rbSelect0ShowIllioni8 -> {
                        ans = 0
                    }
                    R.id.rbSelect1sShowIllioni8 -> {
                        ans = 1

                    }
                    R.id.rbSelect2ShowIllioni8 -> {
                        ans = 2

                    }
                    R.id.rbSelect3sShowIllioni8 -> {
                        ans = 3

                    }
                    R.id.rbSelect4ShowIllioni8 -> {
                        ans = 4

                    }
                    R.id.rbSelect5sShowIllioni8 -> {
                        ans = 5

                    }
                    else -> isChecked = false
                }


                totalPoint += ans

                Log.d("abdo", "total point : $totalPoint ")

                if (isChecked) {


                    indexCurrentQuestion++


                    if (indexCurrentQuestion in 0 until arrQuestion.size) {
                        // go to next question

                        setQuestionInView()


                    } else {
//
                        goToEvaluationPage()
                    }
                }
//            else {
//                Toast.makeText(context, "اختر الاجابات", Toast.LENGTH_SHORT).show()
//
//            }

            }
        }
    }

    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList8Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName = categoryQuestion
        EvaluationSpeNeuralFragment.scoredValue = totalPoint
        EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
        //EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
    }
    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

//            binding.tvQTitleShowIllioni8.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.tvQTitleShowIllioni8.visibility = View.GONE

            binding.tvQRigthTwoAnsShowIllioni8.text =
                "  ${arrQuestion[indexCurrentQuestion].typicalAnswer}"
            binding.img1ShowIllioni8.setImageResource(arrQuestion[indexCurrentQuestion].img1)

            //
            binding.tvQNumIllioni8.text = " رقم ${indexCurrentQuestion + 1} /  ${arrQuestion.size}"

            binding.radioGroupQTwo.clearCheck()

        }
    }
    private fun showAns() {
        binding.imgShowAns.setOnClickListener {
            if (!showedAns) {
                binding.tvQRigthTwoAnsShowIllioni8.visibility = View.VISIBLE
                binding.imgShowAns.setImageResource(R.drawable.baseline_remove_red_eye_24)
                showedAns = true

            } else {
                binding.tvQRigthTwoAnsShowIllioni8.visibility = View.GONE
                binding.imgShowAns.setImageResource(R.drawable.icon_show_ans)
                showedAns = false
            }


        }

    }

    companion object {
        lateinit var categoryQuestion: String
        lateinit var student: Student
    }
}