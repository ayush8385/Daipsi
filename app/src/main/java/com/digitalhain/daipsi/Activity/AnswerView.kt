package com.digitalhain.daipsi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import com.digitalhain.daipsi.R

class AnswerView : AppCompatActivity() {
    lateinit var question:TextView
    lateinit var answer:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer_view)

        question=findViewById(R.id.questionn)
        answer = findViewById(R.id.answerr)

        question.text = intent.getStringExtra("Ques")
        answer.text = intent.getStringExtra("Ans")
        supportActionBar?.title=intent.getStringExtra("course")

    }

    override fun onBackPressed() {
        startActivity(Intent(this,MainActivity::class.java))
        finishAffinity()
        super.onBackPressed()
    }
}