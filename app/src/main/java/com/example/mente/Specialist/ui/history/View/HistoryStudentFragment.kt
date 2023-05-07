package com.example.mente.Specialist.ui.history.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mente.Specialist.HomeSpecialistActivity
import com.example.mente.Specialist.ui.history.adapter.AdapterShowStudentInHistory
import com.example.mente.Student.Model.Student
import com.example.mente.Student.StudentViewModel.StudentViewModel
import com.example.mente.databinding.FragmentHistoryStudentBinding


class HistoryStudentFragment : Fragment() {
 lateinit var binding : FragmentHistoryStudentBinding
    lateinit var studentViewModel: StudentViewModel
    
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
        binding =FragmentHistoryStudentBinding.inflate(inflater ,container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         setActionBar()
         setStudentFromDBToRecycle()

          setSearchView()


    }

    private fun setSearchView() {
        binding.searchViewStudentHistory.addTextChangedListener {text->
         setRecycle(arrStudent.filter{it.name.contains(text.toString())} as MutableList<Student>)

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
        var adapterStudent = AdapterShowStudentInHistory.getInstance()
       // adapterStudent.quizCategory = AddStudentFragment.quizCategory
        binding.recycleViewStudentHistory.layoutManager = LinearLayoutManager(context)
        binding.recycleViewStudentHistory.adapter = adapterStudent
        adapterStudent.setListOrders(it)

    }


    private fun setActionBar() {
        var act = activity as HomeSpecialistActivity
        act.supportActionBar?.title = " سجل الطلاب  "
    }

    companion object {

    }
}