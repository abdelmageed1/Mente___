package com.example.mente.Specialist.ui.Questions.IlllionsQ

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.QuestionsSpecialist.neuralIllinois.DataNeuralIllinois
import com.example.mente.Models.Question
import com.example.mente.R
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList11Binding


class ShowQIlllioniList11Fragment : Fragment() {
    lateinit var binding: FragmentShowQIlllioniList11Binding
    private var ans: Int = 0
    private var totalPoint = 0
    private var indexCurrentQuestion = 0
    lateinit var arrQuestion: MutableList<Question>
    private var showedAns = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrQuestion = DataNeuralIllinois.IllinoisDataList11
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList11Binding.inflate(inflater, container, false)

        binding.pickerIllioni11.minValue = 0
        binding.pickerIllioni11.maxValue = 10

        binding.pickerIllioni11.orientation = LinearLayout.HORIZONTAL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setQuestionInView()

        binding.pickerIllioni11.setOnValueChangedListener { numberPicker, i, i2 ->
            ans = i2
        }

        showAns()

        setBtnNext()


    }
    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

//             binding.tvQTitleShowIllioni11.text = arrQuestion[indexCurrentQuestion].strQuestion
             binding.tvQTitleShowIllioni11.visibility = View.GONE

            binding.tvQRigthTwoAnsShowIllioni11.text = arrQuestion[indexCurrentQuestion].typicalAnswer

            binding.img1ShowIllioni11.setImageResource(arrQuestion[indexCurrentQuestion].img1)

            binding.pickerIllioni11.value = 0


        }
    }

    private fun setBtnNext() {

        binding.btnNext.setOnClickListener {

            totalPoint += ans

            indexCurrentQuestion++
            if (indexCurrentQuestion in 0 until arrQuestion.size
            ) {
                // go to next question

                setQuestionInView()


            } else {

                goToEvaluationPage()
            }
        }


    }

    private fun showAns() {
        binding.imgShowAns.setOnClickListener {
            if (!showedAns) {
                binding.tvQRigthTwoAnsShowIllioni11.visibility = View.VISIBLE
                binding.imgShowAns.setImageResource(R.drawable.baseline_remove_red_eye_24)
                showedAns = true

            } else {
                binding.tvQRigthTwoAnsShowIllioni11.visibility = View.GONE
                binding.imgShowAns.setImageResource(R.drawable.icon_show_ans)
                showedAns = false
            }


        }

    }
    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList11Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName = categoryQuestion
        EvaluationSpeNeuralFragment.scoredValue = totalPoint
        EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
        //EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
    }
    companion object {

        lateinit var categoryQuestion: String
        lateinit var student: Student
    }
}