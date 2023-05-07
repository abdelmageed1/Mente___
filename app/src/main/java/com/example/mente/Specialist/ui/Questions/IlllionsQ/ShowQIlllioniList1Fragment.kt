package com.example.mente.Specialist.ui.Questions.IlllionsQ

import android.annotation.SuppressLint
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
import com.example.mente.databinding.FragmentShowQIlllioniIist1Binding


class ShowQIlllioniList1Fragment : Fragment() {
    private var ansSelected: String = ""
    private var loopDown: Boolean = false
    private var ans: Int = 0
    private var showedAns = false
    lateinit var binding: FragmentShowQIlllioniIist1Binding
    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
    private var totalPoint = 0
    private var isEndRule = false
    private var lastIndexAfterDown = 0
    private var isRoofEnd = false
    private var arrRule = mutableListOf<Boolean>()
    private var arrRoof = mutableListOf<Int>()
    var startIndexDown = 10
    private var arr = IntArray(50){0}
    var pointCategoryList1 = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setActionBar()

        arrQuestion = DataNeuralIllinois.IllinoisDataList1


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniIist1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogicFristQ()
        setQuestionInView()
        setBtnNext()

        showAns()

    }

    private fun showAns() {
        binding.imgShowAns.setOnClickListener {
            if (!showedAns) {
                binding.tvQRigthTwoAns.visibility = View.VISIBLE
                binding.imgShowAns.setImageResource(R.drawable.baseline_remove_red_eye_24)
                showedAns = true

            } else {
                binding.tvQRigthTwoAns.visibility = View.GONE
                binding.imgShowAns.setImageResource(R.drawable.icon_show_ans)
                showedAns = false
            }


        }

    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "الاختبارات "
    }

    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

            binding.tvQTitleTwoAns.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.tvQRigthTwoAns.text =
                "الاجابة الصحيحة  ${arrQuestion[indexCurrentQuestion].typicalAnswer}"


            binding.radioGroupQTwo.clearCheck()

        }
    }


    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//                var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
                ans = 0

                var isChecked = true

                when (optionId) {
                    R.id.rbSelectYes -> {
                        ansSelected = "Yes"
                    }
                    R.id.rbSelectNo -> {
                        ansSelected = "No"
                    }
                    else -> isChecked = false
                }
                if (ansSelected == arrQuestion[indexCurrentQuestion].typicalAnswer) {
                    ans = 1; totalPoint++
                } else {
                    ans = 0
                }


                Log.d("abdo", "total point : $totalPoint ")

                if (isChecked) {


                    if (!isEndRule) {  // هل القاعدة مش اتحققت قبل كدا
                        setRuleIlllioniLIst1(ans)
                        Log.d("abdo", " القاعدة لم تتحق ")

                    }

                    setRoofIlllioniList1(ans)
                    incOrDecIndex()


                    // هل السقف مش اتحقق
                    if (indexCurrentQuestion in 0 until arrQuestion.size && !isRoofEnd) {
                        // go to next question

                        setQuestionInView()


                    } else {

                        goToEvaluationPage()
                    }
                }
//                else {
//                    Toast.makeText(context, "اختر الاجابات", Toast.LENGTH_SHORT).show()
//
//                }
            }
        }
    }


    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQTwoAns_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName = categoryQuestion
        EvaluationSpeNeuralFragment.scoredValue = totalPoint
        EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
        //EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
    }


    @SuppressLint("SuspiciousIndentation")
    private fun setRuleIlllioniLIst1(answer: Int) {
        arr[indexCurrentQuestion] =ans

        if (student.age.year >= 6) {

            if (answer == 1) arrRule.add(true)
            else arrRule.add(false)

            Log.d(
                "abdo", " Rule   index $indexCurrentQuestion   " +
                        "      true ${arrRule.filter { it }.size}  " +
                        "      false ${arrRule.filter { !it }.size} "
            )



            if (!loopDown) {
                if (!arrRule.contains(false) && arrRule.size == 5)  // القاعدة تحققت ,الخمس اسئلة الاولى صحيحيين
                {
                    isEndRule = true
                    arrRule.clear()
                    totalPoint += 10
                    Log.d("abdo", "rule is done  ")
                    return

                }

                if (arrRule.contains(false) && arrRule.size <= 5) {  // القاعدة لم تتحقق

                    lastIndexAfterDown = indexCurrentQuestion
                    indexCurrentQuestion = 10
                    loopDown = true
                    Log.d("abdo", "start  down last index = $lastIndexAfterDown  ")
                    return

                }
            }

          if (loopDown)
            {
                if (indexCurrentQuestion >  0){    // down is true     rule is true
                    if(arr[indexCurrentQuestion] + arr[indexCurrentQuestion + 1] + arr[indexCurrentQuestion + 2] +
                        arr[indexCurrentQuestion + 3] + arr[indexCurrentQuestion + 4] ==5)
                    {
                        isEndRule = true
                        loopDown = false
                        totalPoint += indexCurrentQuestion+1
                        indexCurrentQuestion = lastIndexAfterDown
                        return
                    }
                    else{return}

                }
                else{
                    isEndRule = true
                    loopDown = false
                    indexCurrentQuestion = lastIndexAfterDown++
                    return
                }





            }

        }
        else {
            isEndRule = true
            return
        }



    }


    private fun setRoofIlllioniList1(answer: Int) {
        if (isEndRule){
        if (answer == 0) arrRoof.add(1)  // add one means false
        else arrRoof.add(0)


//        Log.d(
//            "abdo", " Roof   index $indexCurrentQuestion   " +
//                    "      false  ${arrRoof.toString()}  "
//
//        )
        if (arrRoof.contains(1)) {
            if (arrRoof.size <= 7) {
                Log.d(
                    "abdo",
                    "size : ${arrRoof.size}  ${indexCurrentQuestion} +  \"      true ${arrRoof.filter { it == 0 }.size}  " +
                            "     false ${arrRoof.filter { it == 1 }.size}  "
                )


                var sum = sumOfRoofArr(arrRoof)


                if (sum >= 3) {
                    isRoofEnd = true
                    arrRoof.clear()
                    return
                }
            } else {
                arrRoof.clear()
                if (answer == 0) arrRoof.add(1)
            }


        } else arrRoof.clear()


    }
    }


    fun sumOfRoofArr(arr: MutableList<Int>): Int {
        var sum = 0
        for (i in arr)
            sum += i

        return sum
    }

    private fun setLogicFristQ() {

        if (student.age.year < 6) {
            indexCurrentQuestion = 0
        } else if (student.age.year >= 6) {
            indexCurrentQuestion = 10
        }

    }


    fun incOrDecIndex() {
        if (indexCurrentQuestion in 0 until arrQuestion.size)
            if (loopDown)
                indexCurrentQuestion--
            else
                indexCurrentQuestion++

    }


    companion object {
        lateinit var categoryQuestion: String
        lateinit var student: Student
    }


}