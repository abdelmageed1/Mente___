package com.example.mente.Specialist.ui.history.View

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.history.ViewModel.QuizResultVM
import com.example.mente.Specialist.ui.history.adapter.AdapterShowQuizResult
import com.example.mente.Student.Model.Student
import com.example.mente.Student.Repo.RepoStudent
import com.example.mente.constant
import com.example.mente.databinding.FragmentShowRowOfHistoryBinding


class ShowRowOfHistoryFragment : Fragment() {
    lateinit var binding: FragmentShowRowOfHistoryBinding

    lateinit var quizResultVM: QuizResultVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizResultVM = ViewModelProvider(this)[QuizResultVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowRowOfHistoryBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBar()
        selectCategory()
        setViews()

        RemoveStudent()

    }

    @SuppressLint("SuspiciousIndentation")
    private fun RemoveStudent() {
        binding.btnRemoveStudent.setOnClickListener {

            var builder = AlertDialog.Builder(context)
              builder.apply {
                setTitle("تنبيه ")
                setIcon(R.drawable.logo)
                setMessage("هل انت متأكد من حذف هذالطالب")
                setPositiveButton("yes", DialogInterface.OnClickListener { dialog, which ->
                    // delete
                    RepoStudent.getInstance().removeStudent(student.key)
                    findNavController().navigate(R.id.action_showRowOfHistoryFragment_to_historyStudentFragment)
                })

                setCancelable(true)
                show()


                // navigate the previous page
            }
        }
    }


    private fun selectCategory() {
        binding.spinnerCategoryNameQuiz.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    when (position) {
                        0 -> {
                             quizResultVM.getResultQuiz(student.key, constant.quizTypeSpeNeural)
                            setQuizFromDBToRecycle()
                        }
                       1 -> {
                             quizResultVM.getResultQuiz(student.key , constant.quizTypeSpeIIIIinoi)
                           setQuizFromDBToRecycle()
                        }
                        2 -> {
                             quizResultVM.getResultQuiz(student.key , constant.quizTypeSpeFathyElZayat)
                            setQuizFromDBToRecycle()
                        }
                        3 -> {
                             quizResultVM.getResultQuiz(student.key , constant.quizTypeSpeMichaelBest)
                            setQuizFromDBToRecycle()
                        }
                    }
                }
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }

    }

    private fun setQuizFromDBToRecycle() {
        quizResultVM.mGetQuiz.observe(viewLifecycleOwner) {
            if (it != null) {
                setRecycle(it)

                if (it.size == 0) {
                      binding.tvStudentIsEmpty.visibility = View.VISIBLE

                }
                else {binding.tvStudentIsEmpty.visibility = View.GONE}
            }
        }
    }

    private fun setViews() {
        binding.tvStudentName.text = "الاسم : ${student.name}"
        binding.tvStudentAge.text = "السن : ${student.ageStr}"

    }

    private fun setRecycle(it: MutableList<Quiz>) {
        var adapterQuiz = AdapterShowQuizResult.getInstance()
        binding.rvQuizResult.layoutManager = LinearLayoutManager(context)
        binding.rvQuizResult.adapter = adapterQuiz
        adapterQuiz.studentId = student.key
        adapterQuiz.setListOrders(it)

    }

    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = "نتائج الطالب "

    }


    companion object {
        lateinit var student : Student


    }
}