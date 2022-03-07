package com.digitalhain.daipsi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Adapter.QuestionAdapter
import com.digitalhain.daipsi.Adapter.TopicAdapter
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Quizques
import com.digitalhain.daipsi.model.Topic
import org.json.JSONArray

class Startquiz : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: QuestionAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var topic_name:TextView
    var quiz_answer= HashMap<Int,Boolean>()
    lateinit var submit:Button
    var quizlist = ArrayList<Quizques>()
    lateinit var allcorrect:TextView
    lateinit var nextbtn:Button
    var topic_no:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startquiz)

        recyclerView=findViewById(R.id.questionrecycler)
        layoutManager=LinearLayoutManager(this)
        topic_name=findViewById(R.id.topic_name)
        submit=findViewById(R.id.submit)

        allcorrect=findViewById(R.id.allcorrect)
        nextbtn=findViewById(R.id.nxt_btn)


        topic_no=intent.getIntExtra("topic_no",0)

        submit.setOnClickListener {
            var c:Int =0

            for(answer in quiz_answer){
                if(answer.value==true){
                    c++
                }
            }

            if(quiz_answer.size!=0){
                if(c==quiz_answer.size){
                    recyclerView.visibility= View.GONE
                    allcorrect.visibility=View.VISIBLE
                    nextbtn.visibility=View.VISIBLE
                }
                else{
                    recyclerAdapter.updateQues(quizlist)
                    Toast.makeText(applicationContext,"You gave ${quiz_answer.size-c} incorrect answers",Toast.LENGTH_SHORT).show()

                }
            }

        }

        nextbtn.setOnClickListener {
//            startActivity(Intent(this,StartLearning::class.java).putExtra("topic_no",topic_no+1))
            val extras:Extras = applicationContext as Extras
            extras.setpasseed(true)
            finish()
        }

        val topicid :String = intent.getStringExtra("topicid")!!
        topic_name.text=intent.getStringExtra("topic_name")!!+" Quiz"

        val url_topic="https://daipsi.com/Android_Daipsi/Quiz/retrive.php/"
        val queue_topic= Volley.newRequestQueue(this)


        val stringRequest_topic=object : StringRequest(Method.POST,url_topic, Response.Listener {
            try{
                val array = JSONArray(it)

                for (j in 0 until array.length()) {
                    val product = array.getJSONObject(j)

                    val itemJsonRequest = Quizques(
                        product.getString("id"),
                        product.getString("topic_id"),
                        product.getString("Question"),
                        product.getString("option_a"),
                        product.getString("option_b"),
                        product.getString("option_c"),
                        product.getString("option_d"),
                        product.getString("option_true"),
                        false
                    )
                    quizlist.add(itemJsonRequest)

                }
                recyclerAdapter= QuestionAdapter(this,object :QuestionAdapter.onRadioClickListener{
                    override fun addAnswers(ques_no: Int, isCorrect: Boolean) {
                        quiz_answer.put(ques_no,isCorrect)
                        quizlist[ques_no-1].answered=true
                    }

                })
                recyclerView.adapter= recyclerAdapter
                recyclerAdapter.updateQues(quizlist)
                recyclerView.layoutManager=layoutManager


            }
            catch (e:Exception){
                Toast.makeText(applicationContext,e.toString(), Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener {
            Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()

                params.put("topic",topicid)
                return params
            }
        }
        queue_topic.add(stringRequest_topic)


    }
}