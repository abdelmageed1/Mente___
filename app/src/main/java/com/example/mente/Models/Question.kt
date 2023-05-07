package com.example.mente.Models

class Question(
    var id: Int,
    var strQuestion: String ="",
    var rightAns: Int =0,
    var typicalAnswer: String="",
    var img1 : Int =0,
    var img2 : Int =0
)

data  class ModelMichealBest(
    var id: Int,
    var strQuestion: String ="",
    var answer1 : String,
    var answer2 : String,
    var answer3 : String,
    var answer4 : String,
    var answer5 : String,

    )

data class ParentTestModel(
    var id: Int,
    var strQuestion: String,
    var rightAns: Int
)













