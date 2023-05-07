package com.example.mente.Parent.history

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mente.Models.Quiz
import com.example.mente.R
import com.example.mente.Specialist.ui.history.repo.RepoResultOfQuiz
import com.example.mente.databinding.RowOfQuizResultBinding

class AdapterHistoryParent : RecyclerView.Adapter<AdapterHistoryParent.QuizParentResultVH>() {
    var repoResultOfQuiz = RepoResultOfQuiz.getInstance()
    lateinit var context: Context
    var listQuiz: MutableList<Quiz> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizParentResultVH {
        context = parent.context
        return  QuizParentResultVH(
            RowOfQuizResultBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: QuizParentResultVH, position: Int) {
        var current = listQuiz[position]

        holder.tvCategoryQuiz.visibility = View.GONE
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
                    removeQuiz(current)
                })
                setNegativeButton("لا", DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })

                setCancelable(true)
                show()


            }
        }

    }

    override fun getItemCount() = listQuiz.size

    fun setListOrders(list: MutableList<Quiz>) {

        this.listQuiz = list
        notifyDataSetChanged()
    }


    class QuizParentResultVH(item: RowOfQuizResultBinding) : RecyclerView.ViewHolder(item.root) {

        var tvCategoryQuiz = item.tvCategoryQuiz
        var tvQuizName = item.tvQuizName
        var tvQuizResult = item.tvQuizResult
        var tvQuizDate = item.tvQuizDate
        var tvQuizTime = item.tvQuizTime
        var btnRemoveQuizResult = item.btnRemoveQuizResult
    }

    private fun removeQuiz(current: Quiz) {
        repoResultOfQuiz.removeQuizParent(current)
        Toast.makeText(context, "removed", Toast.LENGTH_SHORT).show()
        listQuiz.remove(current)
        notifyDataSetChanged()
    }

    companion object {

        private var instance: AdapterHistoryParent? = null

        fun getInstance(): AdapterHistoryParent {

            if (instance == null) {
                instance = AdapterHistoryParent()
            }
            return instance!!
        }

    }

}