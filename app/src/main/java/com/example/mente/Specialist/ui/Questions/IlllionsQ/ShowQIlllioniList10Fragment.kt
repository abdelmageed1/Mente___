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
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList10Binding
import com.example.mente.databinding.FragmentShowQIlllioniList5Binding

class ShowQIlllioniList10Fragment : Fragment() {
    lateinit var binding: FragmentShowQIlllioniList10Binding
    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
    private var showedAns = false
    private var ans: Int = 0
    private var totalPoint = 0
    private var loopDown: Boolean = false
    private var isEndRule = false
    private var lastIndexAfterDown = 0
    private var isRoofEnd = false
    private var arr = IntArray(30){0}
    private var arrRoof = mutableListOf<Int>()
    private var arrCheckRule = mutableListOf<Int>()

    private var arrRule = mutableListOf<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arrQuestion = DataNeuralIllinois.IllinoisDataList10

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList10Binding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setLogicFristQ()
        setQuestionInView()
        showAns()
        setBtnNext()

    }



    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

            binding.tvQTitleShowIllioni10.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.tvQRigthTwoAnsShowIllioni10.text =
                "  ${arrQuestion[indexCurrentQuestion].typicalAnswer}"


            if (arrQuestion[indexCurrentQuestion].img1 == 0){
                binding.img1ShowIllioni10.visibility = View.GONE
            }
            else{
                binding.img1ShowIllioni10.setImageResource(arrQuestion[indexCurrentQuestion].img1)
            }

            binding.tvQNumIllioni10.text = " رقم ${indexCurrentQuestion + 1} /  ${arrQuestion.size}"

            binding.radioGroupQTwo.clearCheck()

        }
    }

    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//            var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
            ans = 0

            var isChecked = true

            when (optionId) {
                R.id.rbSelect0ShowIllioni10 -> {
                    ans = 0
                }
                R.id.rbSelect1sShowIllioni10-> {
                    ans = 1
                    totalPoint++
                }
                else -> isChecked = false
            }




            Log.d("abdo", "total point : $totalPoint , age = ${student.age}")


            if (isChecked) {


                if (!isEndRule) {  // هل القاعدة مش اتحققت قبل كدا
                    setLogicRule(ans)
                    Log.d("abdo", " القاعدة لم تتحق ")

                }

                setRoof(ans)
                incOrDecIndex()

                // هل السقف مش اتحقق
                if (indexCurrentQuestion in 0 until arrQuestion.size && !isRoofEnd) {
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


    // Roof complete
    private fun setRoof(ans: Int){

        if (isEndRule){


            if (ans==0)
                arrRoof.add(indexCurrentQuestion)

            if (indexCurrentQuestion in 0..18  || indexCurrentQuestion >= 25)
            {
                if (arrRoof.size >= 3 )
                {
                    if (arrRoof[arrRoof.size-1] - arrRoof[arrRoof.size-3] == 2)
                    {
                        isRoofEnd = true
                        return

                    }
                }
            }

            if (indexCurrentQuestion in 18..24  || student.age.year >=6)
            {
                if (arrRoof.size >= 3 )
                {
                    if (arrRoof[arrRoof.size-1] - arrRoof[arrRoof.size-3] == 2)
                    {
                        indexCurrentQuestion = 23
                        return

                    }
                }
            }





        }
    }


    private fun setLogicRule(ans: Int) {

        arr[indexCurrentQuestion] = ans // هحطها فوق





        if(student.age.year < 6)
        {
            arrCheckRule.add(ans)
            if (arrCheckRule.size < 8 )
                    if (arrCheckRule.contains(0)){
//                        Toast.makeText(context, "لم تتحقق القاعدة وتم الخروج منها", Toast.LENGTH_SHORT).show()
                        isEndRule = true
                        return
                    }
            if (arrCheckRule.size == 7 && !arrCheckRule.contains(0))
            {
//                Toast.makeText(context, "تم تتحقق القاعدة", Toast.LENGTH_SHORT).show()
                isEndRule = true
                return
            }

        }



        if (student.age.year >= 6) {


            if (ans == 1) arrRule.add(true)
            else arrRule.add(false)

            if (!loopDown){ // down is false -> UP

                if (!arrRule.contains(false) && arrRule.size == 3) // bool not contain false rule Active
                {
                    isEndRule = true
                    arrRule.clear()
                    totalPoint +=7
                    Log.d("abdo", "rule is done  ")

                    return
                }


                if (arrRule.contains(false) && arrRule.size <= 3) {
                    lastIndexAfterDown = indexCurrentQuestion
                    indexCurrentQuestion = 7
                    loopDown = true
                    Log.d("abdo", "last index = $lastIndexAfterDown  ")
                    return
                }





            }




            if (loopDown)
            {
                if (indexCurrentQuestion >  0){    // down is true     rule is true
                    if(arr[indexCurrentQuestion] + arr[indexCurrentQuestion + 1] + arr[indexCurrentQuestion + 2] ==3)
                    {
                        isEndRule = true
                        loopDown = false
                        totalPoint += indexCurrentQuestion+1
                        indexCurrentQuestion = lastIndexAfterDown++
                        return
                    }
                    else{return}

                }


                else{
                    // هذه الحالة عند عدم تحقق الشرط ابدا والخروج من القاعدة والخروج من الاسئلة
                    isEndRule = true
                    indexCurrentQuestion = lastIndexAfterDown+1
                    loopDown = false

                    return
                }
            }



        }



    }


    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList10Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName = categoryQuestion
        EvaluationSpeNeuralFragment.scoredValue = totalPoint
        EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
        //EvaluationSpeNeuralFragment.numberOfQuestions = arrQuestion.size
    }



    fun incOrDecIndex() {
        if (indexCurrentQuestion in 0 until arrQuestion.size)
            if (loopDown)
                indexCurrentQuestion--
            else
                indexCurrentQuestion++

    }


    private fun setLogicFristQ() {

        if (student.age.year< 6) {
            indexCurrentQuestion = 0
        } else if (student.age.year >= 6) {
            indexCurrentQuestion = 7


        }
    }
    private fun showAns() {
        binding.imgShowAns.setOnClickListener {
            if (!showedAns) {
                binding.tvQRigthTwoAnsShowIllioni10.visibility = View.VISIBLE
                binding.imgShowAns.setImageResource(R.drawable.baseline_remove_red_eye_24)
                showedAns = true

            } else {
                binding.tvQRigthTwoAnsShowIllioni10.visibility = View.GONE
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