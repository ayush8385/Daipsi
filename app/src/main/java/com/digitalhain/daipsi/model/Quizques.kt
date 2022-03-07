package com.digitalhain.daipsi.model

data class Quizques(
    val id:String?,
    val topic_id:String,
    val ques:String,
    val opt_1: String?,
    val opt_2:String?,
    val opt_3:String?,
    val opt_4:String,
    val ans:String,
    var answered:Boolean
)