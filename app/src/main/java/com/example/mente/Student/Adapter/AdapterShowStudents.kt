package com.example.mente.Student.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mente.R
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationMichealBest
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluatonFathyElZayatFragment
import com.example.mente.Specialist.ui.Questions.IlllionsQ.*
import com.example.mente.Student.Model.Student
import com.example.mente.Student.View.AddStudentFragment
import com.example.mente.constant
import com.example.mente.databinding.RowStudentBinding

class AdapterShowStudents : RecyclerView.Adapter<AdapterShowStudents.StudentVH>() {
    lateinit var context: Context
    var listStudent: MutableList<Student> = mutableListOf()
    var quizCategory = ""
    var testName = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentVH {
        context = parent.context
        return StudentVH(
            RowStudentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: StudentVH, position: Int) {
        var current = listStudent[position]
        holder.tvStudentName.text = current.name

        holder.itemView.setOnClickListener {

            defineCategoryAndGetActionToNextPage(it, current)
//            EvaluationSpeNeuralFragment.currentStudent = current
//            it.findNavController().navigate(R.id.action_addStudentFragment_to_showQSupportkFragment)
        }

    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    fun setListOrders(list: MutableList<Student>) {
        this.listStudent = list
        notifyDataSetChanged()
    }


    class StudentVH(item: RowStudentBinding) : RecyclerView.ViewHolder(item.root) {

        var tvStudentName = item.tvRowStudentName
    }


    private fun defineCategoryAndGetActionToNextPage(fabView: View?, currentStudent: Student) {
        when (AddStudentFragment.quizCategory) {
            constant.quizTypeSpeNeural -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_showQSupportkFragment)

                EvaluationSpeNeuralFragment.currentStudent = currentStudent
            }
            ////////////////////////////////////
            constant.quizTypeSpeIIIIinoi -> {


                when (AddStudentFragment.testName) {
                    constant.elIIinoiCategoryList[0] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQTwoAns)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList1Fragment.student = currentStudent

                    }


                    constant.elIIinoiCategoryList[1] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList2Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList2Fragment.student = currentStudent
                    }

                    constant.elIIinoiCategoryList[2] -> {

                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList3Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList3Fragment.student = currentStudent

                    }
                    constant.elIIinoiCategoryList[3] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList4Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList4Fragment.student = currentStudent


                    }

                    constant.elIIinoiCategoryList[4] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList5Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList5Fragment.student = currentStudent

                    }
                    constant.elIIinoiCategoryList[5] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList6Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList6Fragment.student = currentStudent


                    }


                    constant.elIIinoiCategoryList[6] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList7Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList7Fragment.student = currentStudent
                    }


                    constant.elIIinoiCategoryList[7] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList8Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList8Fragment.student = currentStudent

                    }


                    constant.elIIinoiCategoryList[8] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList9Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList9Fragment.student = currentStudent
                    }


                    constant.elIIinoiCategoryList[9] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList10Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList10Fragment.student = currentStudent
                    }


                    constant.elIIinoiCategoryList[10] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList11Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList11Fragment.student = currentStudent

                    }


                    constant.elIIinoiCategoryList[11] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList12Fragment)
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList12Fragment.student = currentStudent


                    }


                }
            }


            ///////////////////
            constant.quizTypeSpeFathyElZayat -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_showQFourAnsFragment)

                EvaluatonFathyElZayatFragment.currentStudent = currentStudent
            }

            constant.quizTypeSpeMichaelBest -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_QSpeMichaelType)
                EvaluationMichealBest.currentStudent = currentStudent

            }

        }
    }


    companion object {

        private var instance: AdapterShowStudents? = null

        fun getInstance(): AdapterShowStudents {

            if (instance == null) {
                instance = AdapterShowStudents()
            }
            return instance!!
        }


    }

}