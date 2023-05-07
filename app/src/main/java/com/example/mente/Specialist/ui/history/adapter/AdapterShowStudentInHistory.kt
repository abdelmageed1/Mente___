package com.example.mente.Specialist.ui.history.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mente.R
import com.example.mente.Specialist.ui.history.View.ShowRowOfHistoryFragment
import com.example.mente.Student.Model.Student
import com.example.mente.databinding.RowStudentBinding

class AdapterShowStudentInHistory :RecyclerView.Adapter<AdapterShowStudentInHistory.StudentVH>()  {
    lateinit var context: Context
    var listStudent: MutableList<Student> = mutableListOf()




    class StudentVH(item: RowStudentBinding) : RecyclerView.ViewHolder(item.root) {

        var tvStudentName = item.tvRowStudentName
    }

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
            ShowRowOfHistoryFragment.student = current

            it.findNavController().navigate(R.id.action_historyStudentFragment_to_showRowOfHistoryFragment)
        }
    }

    override fun getItemCount(): Int {
        return listStudent.size
    }

    fun setListOrders(list: MutableList<Student>) {
        this.listStudent = list
        notifyDataSetChanged()
    }


    companion object {

        private var instance: AdapterShowStudentInHistory? = null

        fun getInstance(): AdapterShowStudentInHistory {

            if (instance == null) {
                instance = AdapterShowStudentInHistory()
            }
            return instance!!
        }


    }

}
