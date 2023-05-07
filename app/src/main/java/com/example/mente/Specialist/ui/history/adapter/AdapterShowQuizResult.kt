package com.example.mente.Specialist.ui.history.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.ui.history.View.ShowRowOfHistoryFragment
import com.example.mente.Specialist.ui.history.repo.RepoResultOfQuiz
import com.example.mente.Student.Repo.RepoStudent
import com.example.mente.databinding.RowOfQuizResultBinding

class AdapterShowQuizResult : RecyclerView.Adapter<AdapterShowQuizResult.QuizResultVH>() {
    lateinit var context: Context
    var listQuiz: MutableList<Quiz> = mutableListOf()
    var repoResultOfQuiz = RepoResultOfQuiz.getInstance()
    var studentId = ""


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizResultVH {
        context = parent.context
        return QuizResultVH(
            RowOfQuizResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QuizResultVH, position: Int) {
        var current = listQuiz[position]
        holder.tvCategoryQuiz.text = " فئة الاختبار : ${current.categoryQuiz} "
        holder.tvQuizName.text = " اسم الاختبار : ${current.nameQuiz}"
        holder.tvQuizResult.text = " نتيجة الاختبار : ${current.resultQuiz}"
        holder.tvQuizDate.text = " تاريخ الاختبار : ${current.date}"
        holder.tvQuizTime.text = " وقت الاختبار : ${current.time}"

        holder.btnRemoveQuizResult.setOnClickListener {


            var builder = AlertDialog.Builder(context)
            builder.apply {
                setTitle("تنبيه ")
                setIcon(R.drawable.logo)
                setMessage("هل انت متأكد من حذف اختبار \" ${current.nameQuiz} \"")
                setPositiveButton("نعم", DialogInterface.OnClickListener { dialog, which ->
                    // delete
                    removeQuiz(current, studentId)
                })
                setNegativeButton("لا" ,DialogInterface.OnClickListener{dialog, which ->
                   dialog.dismiss()
                })

                setCancelable(true)
                show()



            }
        }

    }


    override fun getItemCount(): Int {
        return listQuiz.size
    }


    fun setListOrders(list: MutableList<Quiz>) {

        this.listQuiz = list
        notifyDataSetChanged()
    }

    class QuizResultVH(item: RowOfQuizResultBinding) : RecyclerView.ViewHolder(item.root) {

        var tvCategoryQuiz = item.tvCategoryQuiz
        var tvQuizName = item.tvQuizName
        var tvQuizResult = item.tvQuizResult
        var tvQuizDate = item.tvQuizDate
        var tvQuizTime = item.tvQuizTime
        var btnRemoveQuizResult = item.btnRemoveQuizResult
    }

    private fun removeQuiz(current: Quiz, studentId: String) {
        repoResultOfQuiz.removeQuiz(current, studentId)
        Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show()
        listQuiz.remove(current)
        notifyDataSetChanged()
    }

    companion object {


        private var instance: AdapterShowQuizResult? = null

        fun getInstance(): AdapterShowQuizResult {

            if (instance == null) {
                instance = AdapterShowQuizResult()
            }
            return instance!!
        }


    }


}