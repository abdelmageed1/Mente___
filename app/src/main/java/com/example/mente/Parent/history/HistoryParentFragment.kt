package com.example.mente.Parent.history

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.ui.history.View.ShowRowOfHistoryFragment
import com.example.mente.Specialist.ui.history.ViewModel.QuizResultVM
import com.example.mente.Specialist.ui.history.adapter.AdapterShowQuizResult
import com.example.mente.databinding.FragmentHistoryParentBinding

class HistoryParentFragment : Fragment() {
    lateinit var binding:FragmentHistoryParentBinding
    lateinit var quizResultVM: QuizResultVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        quizResultVM = ViewModelProvider(this)[QuizResultVM::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentHistoryParentBinding.inflate(inflater,container,false)
return binding.root   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizResultVM.getResultParentQuiz()

       setQuizFromDBToRecycle()
    }
    private fun setQuizFromDBToRecycle() {
        quizResultVM.mGetQuizParent.observe(viewLifecycleOwner) {
            if (it != null) {
                setRecycle(it)

                if (it.size == 0) {
                    binding.tvStudentIsEmpty.visibility = View.VISIBLE

                }
                else {binding.tvStudentIsEmpty.visibility = View.GONE}
            }
        }
    }
    private fun setRecycle(it: MutableList<Quiz>) {
        var adapterQuiz = AdapterHistoryParent.getInstance()
        binding.rvQuizResult.layoutManager = LinearLayoutManager(context)
        binding.rvQuizResult.adapter = adapterQuiz
         adapterQuiz.setListOrders(it)

    }
    companion object {

    }
}