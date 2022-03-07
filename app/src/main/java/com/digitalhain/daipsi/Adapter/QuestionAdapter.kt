package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Activity.CourseDetail
import com.digitalhain.daipsi.Activity.Startquiz
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items
import com.digitalhain.daipsi.model.Quizques
import com.digitalhain.daipsi.model.TopCourses
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class QuestionAdapter(val context: Context,val listener:QuestionAdapter.onRadioClickListener):RecyclerView.Adapter<QuestionAdapter.HomeViewHolder>() {
    var items = arrayListOf<Quizques>()
    fun updateQues(list:ArrayList<Quizques>){
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class HomeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val question:TextView =view.findViewById(R.id.question)
        val option_1:RadioButton=view.findViewById(R.id.option_one)
        val option_2:RadioButton=view.findViewById(R.id.option_two)
        val option_3:RadioButton=view.findViewById(R.id.option_three)
        val option_4:RadioButton=view.findViewById(R.id.option_four)
        val correct:TextView=view.findViewById(R.id.correct)

        val radiogrp:RadioGroup=view.findViewById(R.id.radio_grp)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.question_layout,parent,false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var item=items[position]

        val select_id:Int = holder.radiogrp.checkedRadioButtonId

        holder.question.text="Q ${position + 1}. "+item.ques
        holder.option_1.text=item.opt_1
        holder.option_2.text=item.opt_2
        holder.option_3.text=item.opt_3
        holder.option_4.text=item.opt_4

        if (item.answered){
            val id = holder.radiogrp.checkedRadioButtonId
            val radio:RadioButton = holder.view.findViewById(id)
            if(radio.text==item.ans){
                holder.correct.visibility=View.VISIBLE
                holder.correct.text="Correct"
            }
            else{
                holder.correct.visibility=View.VISIBLE
                holder.correct.text="Wrong"
            }
        }


        holder.radiogrp.setOnCheckedChangeListener(object :RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {
                val radio : RadioButton = holder.view.findViewById(p1)
                if(radio.text==item.ans){
                    listener.addAnswers(position+1,true)
                }
                else{
                    listener.addAnswers(position+1,false)
                }
            }
        })

    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface onRadioClickListener{
        fun addAnswers(ques_no:Int,isCorrect:Boolean)
    }
}