package com.example.quiz_application

data class question_structure(

    val id : Int,
    val question : String,
    val image : Int,
    val option1 : String,
    val option2 : String,
    val option3 : String,
    val option4 : String,
    val correct_ans :Int,

)
