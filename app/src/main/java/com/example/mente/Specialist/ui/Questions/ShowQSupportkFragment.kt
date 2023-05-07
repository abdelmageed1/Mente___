package com.example.mente.Specialist.ui.Questions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mente.Data.QuestionsSpecialist.neuralScreening.DataQuestion
import com.example.mente.Models.Question
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.constant
import com.example.mente.databinding.FragmentShowQSupportkBinding

class ShowQSupportkFragment : Fragment() {
    private var indextTv: Int = 0
    lateinit var binding: FragmentShowQSupportkBinding
    lateinit var arrOfTvQId: MutableList<TextView>
    lateinit var allRadioGroup: MutableList<RadioGroup>
    lateinit var rbSelectRight: MutableList<Int>
    lateinit var rbSelectWrong: MutableList<Int>
    lateinit var arrQuestion: MutableList<Question>
    var totalPoint = 0
    var testName = ""
    var pointScored = 0
    var ansTrue = 0
    var ansFalse = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQSupportkBinding.inflate(inflater, container, false)

        // id of tv q
        arrOfTvQId = mutableListOf(
            binding.tvQSupportQ1, binding.tvQSupportQ2, binding.tvQSupportQ3,
            binding.tvQSupportQ4, binding.tvQSupportQ5, binding.tvQSupportQ6,
            binding.tvQSupportQ7, binding.tvQSupportQ8, binding.tvQSupportQ9,
            binding.tvQSupportQ10
        )

        allRadioGroup = mutableListOf(
            binding.radioGroupQSupportQ1, binding.radioGroupQSupportQ2,
            binding.radioGroupQSupportQ3, binding.radioGroupQSupportQ4,
            binding.radioGroupQSupportQ5, binding.radioGroupQSupportQ6,
            binding.radioGroupQSupportQ7, binding.radioGroupQSupportQ8,
            binding.radioGroupQSupportQ9, binding.radioGroupQSupportQ10,
        )

        rbSelectRight = mutableListOf(
            R.id.rbQ1SupportResulRight, R.id.rbQ2SupportResulRight,
            R.id.rbQ3SupportResulRight, R.id.rbQ4SupportResulRight,
            R.id.rbQ5SupportResulRight, R.id.rbQ6SupportResulRight,
            R.id.rbQ7SupportResulRight, R.id.rbQ8SupportResulRight,
            R.id.rbQ9SupportResulRight, R.id.rbQ10SupportResulRight,
        )

        rbSelectWrong = mutableListOf(
            R.id.rbQ1SupportResultWrong, R.id.rbQ2SupportResultWrong,
            R.id.rbQ3SupportResultWrong, R.id.rbQ4SupportResultWrong,
            R.id.rbQ5SupportResultWrong, R.id.rbQ6SupportResultWrong,
            R.id.rbQ7SupportResultWrong, R.id.rbQ8SupportResultWrong,
            R.id.rbQ9SupportResultWrong, R.id.rbQ10SupportResultWrong,
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        initValue()




        when (categoryQuestion) {
            constant.neuralCategoryList[0] -> {
                // وضع الاسئلة ال هشتغل عليها
                arrQuestion = DataQuestion.neuralDataList1
                setQuestions(constant.neuralCategoryList[0], arrQuestion)
                testName = constant.neuralCategoryList[0]


            }
            constant.neuralCategoryList[1] -> {
                arrQuestion = DataQuestion.neuralDataList2
                setQuestions(constant.neuralCategoryList[1], arrQuestion)
                testName = constant.neuralCategoryList[1]
            }
            constant.neuralCategoryList[2] -> {
                arrQuestion = DataQuestion.neuralDataList3
                setQuestions( constant.neuralCategoryList[2] , arrQuestion)
                testName =  constant.neuralCategoryList[2]

            }

            constant.neuralCategoryList[3]  -> {
//////////////////////////////////////////////
                arrQuestion = DataQuestion.neuralDataList4
                setQuestions(constant.neuralCategoryList[3],arrQuestion)
                testName = constant.neuralCategoryList[3]
            }

            constant.neuralCategoryList[4]  -> {
                arrQuestion = DataQuestion.neuralDataList5
                setQuestions(constant.neuralCategoryList[4] , arrQuestion)
                testName = constant.neuralCategoryList[4]
            }

            constant.neuralCategoryList[5]  -> {
                arrQuestion = DataQuestion.neuralDataList6
                setQuestions(constant.neuralCategoryList[5], arrQuestion)
                testName = constant.neuralCategoryList[5]
            }

            constant.neuralCategoryList[6] -> {
                arrQuestion = DataQuestion.neuralDataList7
                setQuestions(constant.neuralCategoryList[6], arrQuestion)
                testName = constant.neuralCategoryList[6]
            }

            constant.neuralCategoryList[7] -> {
                arrQuestion = DataQuestion.neuralDataList8
                setQuestions(constant.neuralCategoryList[7], arrQuestion)
                testName = constant.neuralCategoryList[7]
            }

            constant.neuralCategoryList[8] -> {
                arrQuestion = DataQuestion.neuralDataList9
                setQuestions(constant.neuralCategoryList[8], arrQuestion)
                testName = constant.neuralCategoryList[8]
            }





            constant.neuralCategoryList[9] -> {
                arrQuestion = DataQuestion.neuralDataList10
                setQuestions(constant.neuralCategoryList[9], arrQuestion)
                testName = constant.neuralCategoryList[9]
            }

            constant.neuralCategoryList[10] -> {
                arrQuestion = DataQuestion.neuralDataList11
                setQuestions(constant.neuralCategoryList[10], arrQuestion)
                testName = constant.neuralCategoryList[10]
            }

            constant.neuralCategoryList[11] -> {
                arrQuestion = DataQuestion.neuralDataList12
                setQuestions(constant.neuralCategoryList[11], arrQuestion)
                testName = constant.neuralCategoryList[11]
            }

            constant.neuralCategoryList[12] -> {
                arrQuestion = DataQuestion.neuralDataList13
                setQuestions(constant.neuralCategoryList[12], arrQuestion)
                testName = constant.neuralCategoryList[12]
            }

            constant.neuralCategoryList[13] -> {
                arrQuestion = DataQuestion.neuralDataList14
                setQuestions(constant.neuralCategoryList[13], arrQuestion)
                testName = constant.neuralCategoryList[13]
            }

            constant.neuralCategoryList[14] -> {
                arrQuestion = DataQuestion.neuralDataList15
                setQuestions(constant.neuralCategoryList[14], arrQuestion)
                testName = constant.neuralCategoryList[14]
            }


        }


        setEvaluation()


    }

    private fun setEvaluation() {

        binding.btnEvaluation.setOnClickListener {

            var isChecked = true

            for (i in 0..indextTv) {
                var radioSelectedId = allRadioGroup[i].checkedRadioButtonId

                when (radioSelectedId) {
                    rbSelectRight[i] -> {
                        ansTrue += 1
                        pointScored += arrQuestion[i].rightAns
                    }
                    rbSelectWrong[i] -> {
                        ansFalse += 1
                    }
                    else -> {
                        isChecked = false
                    }
                }

                if (!isChecked) {
                    Toast.makeText(context, "اكمل الاجابات", Toast.LENGTH_SHORT).show()
                    break
                }

            }

            if (isChecked) {
                findNavController().navigate(R.id.action_showQSupportkFragment_to_evaluationSpeNeuralFragment)
                EvaluationSpeNeuralFragment.testName = testName
                EvaluationSpeNeuralFragment.totalValue = totalPoint
                EvaluationSpeNeuralFragment.scoredValue = pointScored
                EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
                EvaluationSpeNeuralFragment.numberOfRightAns = ansTrue


            }


        }
    }

    private fun initValue() {
        totalPoint = 0
        ansTrue = 0
        ansFalse = 0
        pointScored = 0
    }

    private fun setQuestions(tittle: String, arrQ: MutableList<Question>) {
        binding.tvQSupportTitle.text = tittle
        //   indextTv = 0
        for (i in arrQ) {
            indextTv = (i.id) - 1
            arrOfTvQId[indextTv].text = i.strQuestion

            totalPoint += i.rightAns
        }

        // hide views not used
        for (i in indextTv + 1 until arrOfTvQId.size) {
            arrOfTvQId[i].visibility = View.GONE
            allRadioGroup[i].visibility = View.GONE
        }
    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "الاختبارات "
    }

    companion object {
        lateinit var categoryQuestion: String

    }
}