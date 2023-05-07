package com.example.mente.Student.Model

import android.os.Parcelable
import com.example.mente.Models.Quiz
import kotlinx.parcelize.Parcelize

@Parcelize
class Student (
    var name :String = "",
    var age :StudentAge = StudentAge(0,0,0),
    var mentalAge :StudentAge = StudentAge(0,0,0),// ال بتدخل من الاخصائي العمر العقلى
    var key  :String = "",
    var quiz : MutableList<Quiz> = mutableListOf(),
    var ageStr :String = ""


): Parcelable


@Parcelize
class StudentAge (
    var year:Int =0 ,
    var month:Int =0 ,
    var day:Int =0 ,

    ): Parcelable


