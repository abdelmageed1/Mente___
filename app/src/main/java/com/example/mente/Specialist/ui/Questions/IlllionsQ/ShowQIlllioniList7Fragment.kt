package com.example.mente.Specialist.ui.Questions.IlllionsQ

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.QuestionsSpecialist.neuralIllinois.DataNeuralIllinois
import com.example.mente.Models.Question
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList7Binding

class ShowQIlllioniList7Fragment : Fragment() {
    lateinit var binding: FragmentShowQIlllioniList7Binding
    private var showedAns = false
    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
    private var isRoofEnd = false
    private var arrRoof = mutableListOf<Int>()

    private var ans: Int = 0
    private var totalPoint = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setActionBar()
        arrQuestion = DataNeuralIllinois.IllinoisDataList7


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList7Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        setQuestionInView()
        setBtnNext()
        showAns()

    }

    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {
//                var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
                ans = 0

                var isChecked = true

                when (optionId) {
                    R.id.rbSelectNoShowIllioni7 -> {
                        ans = 0
                    }
                    R.id.rbSelectYesShowIllioni7 -> {
                        ans = 1
                        totalPoint++
                    }
                    else -> isChecked = false
                }




                Log.d("abdo", "total point : $totalPoint ")

                if (isChecked) {


                    indexCurrentQuestion++

                    setRoof(ans)

                    if (indexCurrentQuestion in 0 until arrQuestion.size && !isRoofEnd
                    ) {
                        // go to next question

                        setQuestionInView()


                    } else {

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

    private fun setRoof(answer: Int) {
        if (student.age.year < 6) {
            if (answer == 0) {
                arrRoof.add(1)
                Log.d("farha", "setRoof: false = ${arrRoof.size} ")
                if (arrRoof.size == 6) {

                    arrRoof.clear()
                    isRoofEnd = true
                    return
                }
            } else {
                arrRoof.clear()
            }
        }


    }


    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList7Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName = categoryQuestion
        EvaluationSpeNeuralFragment.scoredValue = totalPoint
        EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
        //EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
    }

    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

          //  binding.tvQTitleShowIllioni7.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.tvQTitleShowIllioni7.visibility =View.GONE

            binding.tvQRigthTwoAnsShowIllioni7.text =
                "الاجابة الصحيحة  ${arrQuestion[indexCurrentQuestion].typicalAnswer}"
            binding.img1ShowIllioni7.setImageResource(arrQuestion[indexCurrentQuestion].img1)


            binding.radioGroupQTwo.clearCheck()

            binding.tvQNumIllioni7.text = " رقم ${indexCurrentQuestion + 1} /  ${arrQuestion.size}"

        }
    }

    private fun showAns() {
        binding.imgShowAns.setOnClickListener {
            if (!showedAns) {
                binding.tvQRigthTwoAnsShowIllioni7.visibility = View.VISIBLE
                binding.imgShowAns.setImageResource(R.drawable.baseline_remove_red_eye_24)
                showedAns = true

            } else {
                binding.tvQRigthTwoAnsShowIllioni7.visibility = View.GONE
                binding.imgShowAns.setImageResource(R.drawable.icon_show_ans)
                showedAns = false
            }


        }

    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "الاختبارات "
    }


    companion object {
        lateinit var categoryQuestion: String
        lateinit var student: Student
    }

}