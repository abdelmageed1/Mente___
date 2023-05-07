package com.example.mente.Student.View

import android.app.AlertDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationMichealBest
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluationSpeNeuralFragment
import com.example.mente.Specialist.ui.Questions.Evaluation.EvaluatonFathyElZayatFragment
import com.example.mente.Specialist.ui.Questions.IlllionsQ.*
import com.example.mente.Student.Adapter.AdapterShowStudents
import com.example.mente.Student.Model.Student
import com.example.mente.Student.Model.StudentAge
import com.example.mente.Student.Repo.RepoStudent
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.constant
import com.example.mente.databinding.FragmentAddStudentBinding
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Period
import java.time.Year

class AddStudentFragment : Fragment() {
    lateinit var binding: FragmentAddStudentBinding
    lateinit var studentViewModel: StudentViewModel
    lateinit var currentStudent: Student
    var arrStudent = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        studentViewModel = ViewModelProvider(this)[StudentViewModel::class.java]
        studentViewModel.getStudent()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddStudentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setActionBar()
        setQuizType()
        addStudent()

        setStudentFromDBToRecycle()

        setSearchView()
    }

    private fun setSearchView() {
        binding.searchViewStudent.addTextChangedListener { text ->
            //   if(text!!.isNotEmpty())
            setRecycle(arrStudent.filter { it.name.contains(text.toString()) } as MutableList<Student>)

        }
    }


    private fun setQuizType() {
        when (testName) {
            in constant.neuralCategoryList -> {
                quizCategory = constant.quizTypeSpeNeural
            }
            in constant.elIIinoiCategoryList -> {
                quizCategory = constant.quizTypeSpeIIIIinoi
            }
            in constant.fathyElZayatCategoryList -> {
                quizCategory = constant.quizTypeSpeFathyElZayat
            }
            in constant.MichaelBestCategoryList -> {
                quizCategory = constant.quizTypeSpeMichaelBest
            }
        }
    }


    private fun setStudentFromDBToRecycle() {
        studentViewModel.getMutableStudents().observe(viewLifecycleOwner) {
            if (it != null) {
                arrStudent = it
                setRecycle(it)


                if (it.size == 0) {
                    binding.tvStudentIsEmpty.visibility = View.VISIBLE

                }
            }
        }
    }

    private fun setRecycle(it: MutableList<Student>) {
        var adapterStudent = AdapterShowStudents.getInstance()
        adapterStudent.quizCategory = quizCategory
        binding.recycleViewStudent.layoutManager = LinearLayoutManager(context)
        binding.recycleViewStudent.adapter = adapterStudent
        adapterStudent.setListOrders(it)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun addStudent() {
        binding.fabAddStudent.setOnClickListener { fabView ->

            var builder = AlertDialog.Builder(context)
            builder.apply {

                setView(R.layout.layout_add_student)
                var alter = builder.show()

                var btnSaveData = alter.findViewById<Button>(R.id.btnAddNewUser)
                btnSaveData.setOnClickListener {
                    var name = alter.findViewById<EditText>(R.id.et_nameOfStudent).text.toString()
                    var yearsDate = alter.findViewById<EditText>(R.id.et_AgeYearOfStudent).text.toString()
                    var monthDate = alter.findViewById<EditText>(R.id.et_AgeMonthOfStudent).text.toString()
                    var dayDate = alter.findViewById<EditText>(R.id.et_AgeDayOfStudent).text.toString()


                    if (name.isNotEmpty() && yearsDate.isNotEmpty() &&  monthDate.isNotEmpty() && dayDate.isNotEmpty() ) {

                        var dateIsTrue = false
//                        var yearNow = LocalDateTime.now().year
                        var yearNow = Year.now().value
                        if (yearsDate.toInt() in yearNow-18..yearNow && monthDate.toInt() in 1..12 && dayDate.toInt() in 1..31)
                        {
                            dateIsTrue = true
                        }

                        if (dateIsTrue) {
                            val birthDate = LocalDate.of(yearsDate.toInt(),monthDate.toInt(),dayDate.toInt())
                            val age = calculateAge(birthDate)
                            val ageStr=" ${age.years} سنة و ${age.months} شهر و ${age.days}  يوم"






                            var student = Student(name= name,age= StudentAge(age.years,age.months,age.days), ageStr = ageStr)
                            setStudentInfoToDB(student)

                            studentViewModel.mAddStudentFailure.observe(viewLifecycleOwner) { message ->
                                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            }
                            studentViewModel.mAddStudentSuccess.observe(viewLifecycleOwner) {
                                if (it) {
                                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                                    alter.dismiss()

                                    defineCategoryAndGetActionToNextPage(fabView)

                                }
                            }


                        } else {
                            Toast.makeText(
                                context,
                                "عمر الطالب غير مناسب ",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } else {
                        Toast.makeText(
                            context,
                            "اكتب الاسم والعمر للطالب",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

        }
    }


    private fun setStudentInfoToDB(student: Student) {
        studentViewModel.addNewStudent(student)
    }

    private fun defineCategoryAndGetActionToNextPage(fabView: View?) {
        when (quizCategory) {
            constant.quizTypeSpeNeural -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_showQSupportkFragment)
                getStudentHasAddedNow()
                EvaluationSpeNeuralFragment.currentStudent =
                    currentStudent
            }
            ////////////////////////////////////
            constant.quizTypeSpeIIIIinoi -> {

                when (testName) {
                    constant.elIIinoiCategoryList[0] -> {

                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQTwoAns)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList1Fragment.student = currentStudent
                    }


                    constant.elIIinoiCategoryList[1] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList2Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList2Fragment.student = currentStudent
                    }



                    constant.elIIinoiCategoryList[2] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList3Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList3Fragment.student = currentStudent
                    }

                    constant.elIIinoiCategoryList[3] -> {

                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList4Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent = currentStudent
                        ShowQIlllioniList4Fragment.student = currentStudent

                    }



                    constant.elIIinoiCategoryList[4] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList5Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList5Fragment.student = currentStudent
                    }





                    constant.elIIinoiCategoryList[5] -> {

                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList6Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList6Fragment.student = currentStudent
                    }




                    constant.elIIinoiCategoryList[6] -> {

                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList7Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList7Fragment.student = currentStudent
                    }





                    constant.elIIinoiCategoryList[7] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList8Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList8Fragment.student = currentStudent
                    }



                    constant.elIIinoiCategoryList[8] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList9Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList9Fragment.student = currentStudent
                    }






                    constant.elIIinoiCategoryList[9] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList10Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList10Fragment.student = currentStudent

                    }


                    constant.elIIinoiCategoryList[10] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList11Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList11Fragment.student = currentStudent
                    }


                    constant.elIIinoiCategoryList[11] -> {
                        fabView?.findNavController()
                            ?.navigate(R.id.action_addStudentFragment_to_showQIlllioniList12Fragment)
                        getStudentHasAddedNow()
                        EvaluationSpeNeuralFragment.currentStudent =
                            currentStudent
                        ShowQIlllioniList12Fragment.student = currentStudent

                    }



                }


            }


            ///////////
            constant.quizTypeSpeFathyElZayat -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_showQFourAnsFragment)
                getStudentHasAddedNow()
                EvaluatonFathyElZayatFragment.currentStudent =
                    currentStudent
            }

            constant.quizTypeSpeMichaelBest -> {
                fabView?.findNavController()
                    ?.navigate(R.id.action_addStudentFragment_to_QSpeMichaelType)
                EvaluationMichealBest.currentStudent = currentStudent
            }

        }
    }


    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = " اختر الطالب  "
    }

    fun getStudentHasAddedNow() {
        currentStudent = RepoStudent.getInstance().studentAddedNow
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun calculateAge(birthDate: LocalDate): Period {
        return Period.between(birthDate, LocalDate.now())
    }



    companion object {
        lateinit var testName: String
        lateinit var quizCategory: String
    }
}