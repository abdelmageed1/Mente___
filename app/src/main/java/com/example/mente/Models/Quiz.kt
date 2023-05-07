package com.example.mente.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize



    @Parcelize
    class Quiz  (
        var nameQuiz :String = "" ,
        var categoryQuiz :String = "" ,
        var resultQuiz :String = "" ,
        var evaluationOfQuiz :String = "" ,
        var date :String= "",
        var time:String ="",



    ) : Parcelable
