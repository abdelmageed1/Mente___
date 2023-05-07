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
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.FragmentShowQIlllioniList11Binding
import com.example.mente.databinding.FragmentShowQIlllioniList12Binding


class ShowQIlllioniList12Fragment : Fragment() {

    lateinit var binding: FragmentShowQIlllioniList12Binding
    lateinit var arrQuestion: MutableList<Question>
    private var indexCurrentQuestion = 0
     private var ans: Int = 0
    private var indexFollowingGreter4: Int = 0
    private var indexFollowingGreter6: Int = 1
    private var totalPoint = 0
    private var loopDown: Boolean = false
    private var isEndRule = false
    private var lastIndexAfterDown = -1
    private var isRoofEnd = false
    private var isFollowStar = true



    private var arr = IntArray(30){0}
    private var arrStar= intArrayOf(1,4,7,11,16,21)
    private var arrRoof = mutableListOf<Int>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arrQuestion = DataNeuralIllinois.IllinoisDataList12
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowQIlllioniList12Binding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setLogicFristQ()
        setQuestionInView()

        setBtnNext()



    }


    private fun setBtnNext() {

        binding.radioGroupQTwo.setOnCheckedChangeListener { radioGroup, optionId ->
            run {

//            var rbSelected = binding.radioGroupQTwo.checkedRadioButtonId
                ans = 0

                var isChecked = true

                when (optionId) {
                    R.id.rbSelect0ShowIllioni12 -> {
                        ans = 0
                    }

                    R.id.rbSelect1ShowIllioni12 -> {
                        ans = 1
                        totalPoint++
                    }
                    R.id.rbSelect2ShowIllioni12 -> {
                        ans = 2
                        totalPoint += 2
                    }
                    else -> isChecked = false
                }




                Log.d("abdo", "total point : $totalPoint ")

                if (isChecked) {


                    if (!isEndRule) {
                        Log.d("abdo", " القاعدة لم تتحق ")          // هل القاعدة مش اتحققت قبل كدا
                        setLogicRule(ans)


                    }

                    setRoof(ans)

                    if (!isFollowStar)
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
    private fun setQuestionInView() {

       if (indexCurrentQuestion < arrQuestion.size) {

           if (indexCurrentQuestion in arrStar && loopDown){
               indexCurrentQuestion--
           }

            binding.tvQTitleShowIllioni12.text = "  ${arrQuestion[indexCurrentQuestion].id} / ${arrQuestion.size}"
            binding.img1ShowIllioni12.setImageResource(arrQuestion[indexCurrentQuestion].img1)
            binding.radioGroupQTwo.clearCheck()

        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setLogicRule(ans: Int) {

        arr[indexCurrentQuestion] =ans
        Log.d("abdo", " index= $indexCurrentQuestion ")
        if (student.age.year >= 4) {

            if (!loopDown) {

                if (isFollowStar) {
                    if (indexCurrentQuestion < 21) {

                       if (ans ==2 ) {

                           indexCurrentQuestion =
                               if (student.age.year in 4..6) arrStar[++indexFollowingGreter4]
                               else arrStar[++indexFollowingGreter6]

                                return
                       }


                        if (ans != 2) {
                            Log.d("abdo", " ans !!!!=2  ")
                            isFollowStar = false
                            loopDown = true
                        }

                    } else {
                        isFollowStar = false
                        isEndRule = true
                        totalPoint = 2 * 21
                        return
                    }


                }

            }




            if (loopDown) {


                if(lastIndexAfterDown == -1 ){lastIndexAfterDown = indexCurrentQuestion}
                Log.d("abdo", " loopDown  and lastIndexAfterDown =$lastIndexAfterDown and indexCurrentQuestion =$indexCurrentQuestion" )
                if (indexCurrentQuestion > 0) {    // down is true     rule is true

                    Log.d("abdo", " indexCurrentQuestion  >  0 and isFollowStar =$isFollowStar  ")


                       // if (arr[index] + arr[index + 1] + arr[index + 2] == 6  ) {
                    var sum = 0
                    var index = indexCurrentQuestion
                    for ( i in 0..2)
                     {
                         if (index !in arrStar )
                         {
                             sum +=arr[index++]
                         }
                         else {
                             index++
                             sum +=arr[index++]
                         }
                         Log.d("abdo", " index !in arrStar = $index   ")
                     }

                       // if (arr[indexCurrentQuestion] + arr[indexCurrentQuestion + 1] + arr[indexCurrentQuestion + 2]    == 6  )
                        if (sum   == 6  )
                        {    Log.d("abdo", " ans  sum == 6  ")
                            isEndRule = true
                            loopDown = false
                            totalPoint += indexCurrentQuestion * 2
                            indexCurrentQuestion = lastIndexAfterDown++
                            return
                        }
                    }

                else{ // وصلت للصفر ومش حقق القاعدة
                    isFollowStar = false
                    isEndRule = true
                    loopDown = false
                    indexCurrentQuestion = lastIndexAfterDown
                    return
                }


                }



            }

        else {
            isEndRule = true
            Log.d("abdo", "isEndRule = $isEndRule  ")
            return
        }


    }




    private fun goToEvaluationPage() {
        findNavController().navigate(R.id.action_showQIlllioniList12Fragment_to_evaluationSpeNeuralFragment)
        EvaluationSpeNeuralFragment.testName =  categoryQuestion
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

    private fun setRoof(ans: Int){

        if (isEndRule) {
            if (ans==0)
                arrRoof.add(indexCurrentQuestion)

            if (arrRoof.size >= 2 )
            {
                if (arrRoof[arrRoof.size-1] - arrRoof[arrRoof.size-2] == 1)
                {
                    isRoofEnd = true
                    return

                }
            }}

    }


    private fun setLogicFristQ() {

        if ( student.age.year <= 4) {
            indexCurrentQuestion = 0
            isFollowStar  = false
        } else if ( student.age.year in 4..6) {
            indexCurrentQuestion = 1
        }
        else
            indexCurrentQuestion = 4



    }



    companion object {

        lateinit var categoryQuestion: String
        lateinit var student: Student
    }
}