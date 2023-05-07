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
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList2Binding
import com.example.mente.databinding.FragmentShowQIlllioniList3Binding
import com.example.mente.databinding.FragmentShowQIlllioniList7Binding

class ShowQIlllioniList3Fragment : Fragment() {
    lateinit var binding: FragmentShowQIlllioniList3Binding

    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
    private var showedAns = false
    private var ans: Int = 0
    private var totalPoint = 0
    private var loopDown: Boolean = false
    private var isEndRule = false
    private var lastIndexAfterDown = 0
    private var isRoofEnd = false
    private var arr = IntArray(42){0}
    private var arrRoof = mutableListOf<Int>()
    private var arrRule = mutableListOf<Boolean>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arrQuestion = DataNeuralIllinois.IllinoisDataList3


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList3Binding.inflate(inflater,container,false)
        return binding.root}




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogicFristQ()
        setQuestionInView()

        setBtnNext()



    }
    private fun setRoof(ans: Int){

        if (isEndRule){
            if (ans==0)
                arrRoof.add(indexCurrentQuestion)

            if (arrRoof.size >= 3 )
            {
                if (arrRoof[arrRoof.size-1] - arrRoof[arrRoof.size-3] == 2)
                {
                    isRoofEnd = true
                    return

                }
            }

        }
    }
    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//                var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
                ans = 0

                var isChecked = true

                when (optionId) {
                    R.id.rbSelectNoShowIllioni3 -> {
                        ans = 0
                    }
                    R.id.rbSelectYesShowIllioni3 -> {
                        ans = 1
                        totalPoint++
                    }
                    else -> isChecked = false
                }




                Log.d("abdo", "total point : $totalPoint ")

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
//                else {
//                    Toast.makeText(context, "اختر الاجابات", Toast.LENGTH_SHORT).show()
//
//                }

            }
        }
    }
    private fun setQuestionInView() {


        if (indexCurrentQuestion < arrQuestion.size) {

            binding.tvQTitleShowIllioni3.text = arrQuestion[indexCurrentQuestion].strQuestion
            binding.tvQRigthShowIllioni3.text =
                "الاجابة الصحيحة  ${arrQuestion[indexCurrentQuestion].typicalAnswer}"

            //

            binding.radioGroupQTwo.clearCheck()

        }
    }

    private fun setLogicRule(ans: Int) {

        arr[indexCurrentQuestion] =ans
        Log.d("abdo", " index= $indexCurrentQuestion ")
        if (student.age.year >= 6) {


            if (ans == 1) arrRule.add(true)
            else arrRule.add(false)

            if (!loopDown){ // down is false -> UP

                if (!arrRule.contains(false) && arrRule.size == 3) // bool not contain false rule Active
                {
                    isEndRule = true
                    arrRule.clear()
                    totalPoint +=20
                    Log.d("abdo", "rule is done  ")

                    return
                }


                if (arrRule.contains(false) && arrRule.size <= 3) {
                    lastIndexAfterDown = indexCurrentQuestion
                    indexCurrentQuestion = 10
                    loopDown = true
                    Log.d("abdo", "last index = $lastIndexAfterDown  ")
                    return
                }





            }




            if (loopDown)
            {
                if (indexCurrentQuestion > 0){    // down is true     rule is true
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
                    indexCurrentQuestion = 0
                    totalPoint = 0
                    student.age.year = 5      // فى حالة الطلفل محققش القاعدة هيتعامل ك طفل اقل من 6 سنين
                    loopDown = false
                    return
                }
            }



        }
        else {
            isEndRule = true
            return
        }


    }


    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList3Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName =categoryQuestion
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

        if (student.age.year < 6) {
            indexCurrentQuestion = 0
        } else if (student.age.year >= 6) {
            indexCurrentQuestion = 10


        }
    }



    companion object {
        lateinit var categoryQuestion: String
        lateinit var student: Student
    }
}