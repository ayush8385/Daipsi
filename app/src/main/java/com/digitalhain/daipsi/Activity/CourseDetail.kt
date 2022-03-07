package com.digitalhain.daipsi.Activity

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.drawable.GradientDrawable
import android.media.MediaDescription
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import com.digitalhain.daipsi.R
import java.net.URI
import android.util.DisplayMetrics
import android.util.Log
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.FragmentActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Adapter.SearchAdapter
import com.digitalhain.daipsi.model.Subject
import com.digitalhain.daipsi.model.TopCourses
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import java.io.IOException
import java.nio.file.attribute.AclEntry
import java.util.ArrayList
import android.R.string.no
import java.lang.RuntimeException
import java.lang.reflect.Method


class CourseDetail : AppCompatActivity(),PaymentResultListener {
    lateinit var name:TextView
    lateinit var teacher:TextView
    lateinit var price:TextView
    lateinit var name_for:TextView
    lateinit var count:TextView
    lateinit var description: TextView
    lateinit var requirement:TextView
    lateinit var buy:RelativeLayout
    lateinit var buy_txt:TextView
    lateinit var progressBar: ProgressBar
   var courseCode:String?=""
    var amount:String?=""

    lateinit var player: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

        name = findViewById(R.id.course_name)
        name_for = findViewById(R.id.course_name_with)
        teacher = findViewById(R.id.course_by)
        price = findViewById(R.id.price)
        count = findViewById(R.id.count)
        description = findViewById(R.id.description)
        requirement = findViewById(R.id.reqs)
        buy = findViewById(R.id.buy_btn)
        buy_txt = findViewById(R.id.buy_txt)
        progressBar = findViewById(R.id.progressBar)

        val sharedPreferences = getSharedPreferences("Shared Preference", Context.MODE_PRIVATE)

        val details = intent.getParcelableExtra<TopCourses>("c_det")

        courseCode = details!!.code
        amount = details!!.price

        name.text = details!!.name
        name_for.text = details!!.name + " for absolute Beginners"
        teacher.text = "Officially created by " + details!!.by
        price.text = details!!.price + " INR"
        count.text = "⦿ " + details!!.count + " Videos "
        description.text = details!!.desc
        requirement.text = details!!.req


        val url = "https://daipsi.com/Android_Daipsi/VerifyCourses/verify.php/"
        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest =
            object : StringRequest(Method.POST, url, com.android.volley.Response.Listener {
                try {
                    if (it.equals("success")) {
                        progressBar.visibility = View.GONE
                        buy_txt.text = "START LEARNING"
                    } else {
                        progressBar.visibility = View.GONE
                        buy_txt.text = "BUY NOW"
                    }
                } catch (e: Exception) {
                    Toast.makeText(applicationContext, "Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }, com.android.volley.Response.ErrorListener {
                Toast.makeText(applicationContext, "Volley error occurred!!!", Toast.LENGTH_SHORT)
                    .show()
            }) {
                override fun getParams(): MutableMap<String, String> {
                    val params = HashMap<String, String>()
                    params.put("email", sharedPreferences.getString("email", "")!!)
                    params.put("courseCode", courseCode!!)
                    return params
                }
            }
        queue.add(jsonObjectRequest)


        buy.setOnClickListener {
            if (buy_txt.text == "START LEARNING") {
                val intent = Intent(this, StartLearning::class.java)
                intent.putExtra("Course_name",details!!.name)
                intent.putExtra("Course_id",details!!.code)
                intent.putExtra("topic_no",0)
                startActivity(intent)
            } else {
                startPayment()
            }
        }


        player = findViewById(R.id.videolayout)


        val mediaC = MediaController(this);
        mediaC.setAnchorView(player)
        player.setMediaController(mediaC)


        player.setVideoURI(Uri.parse("https://daipsi.com/Android_Daipsi/CourseVideos/" + details.video))
        player.requestFocus()
        player.start()
    }


    fun startPayment() {
        val activity: Activity = this@CourseDetail
        val co = Checkout()
        co.setKeyID("rzp_test_rL8yif4HUwBsGI")
        co.setImage(R.drawable.app_logo)

        try {
            val options = JSONObject()
            options.put("name","Daipsi")
            options.put("description","Amount")
            options.put("currency","INR")
            options.put("amount",((amount?.toInt())!!*100).toString())
            options.put("send_sms_hash",true);

            val prefill = JSONObject()
            prefill.put("email","123cdsjhgj@gmail.com")
            prefill.put("contact","8853009697")

            options.put("prefill",prefill)
            co.open(activity,options)
        }catch (e: Exception){
            Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    override fun onPaymentSuccess(p0: String?) {
        val sharedPreferences = getSharedPreferences("Shared Preference",Context.MODE_PRIVATE)

        val url="https://daipsi.com/Android_Daipsi/PaymentGateway/insert.php/"
        val queue= Volley.newRequestQueue(this)
        val jsonObjectRequest=object : StringRequest(Method.POST,url,com.android.volley.Response.Listener {
            try{
//                if(it.equals("success")){
//                    Toast.makeText(applicationContext,"Payment Id saved to database", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    //Toast.makeText(applicationContext,"Error While saving payment id", Toast.LENGTH_LONG).show()
//                }
            }
            catch (e:Exception){
                Toast.makeText(applicationContext,"Error Occurred",Toast.LENGTH_SHORT).show()
            }
        },com.android.volley.Response.ErrorListener {
            Toast.makeText(applicationContext, "Volley error occurred!!!", Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params.put("email", sharedPreferences.getString("email","")!!)
                params.put("courseCode", courseCode!!)
                params.put("expiryDate", "")
                params.put("purchaseDate", "")
                params.put("payment_id", p0!!)
                params.put("status","success")
                params.put("tokenKey","")
                params.put("amount", amount!!)
                return params
            }
        }
        queue.add(jsonObjectRequest)

        val mycourseurl="https://daipsi.com/Android_Daipsi/MyCourses/my_courses.php/"
        val queue2= Volley.newRequestQueue(this)
        val jsonObjectRequest2=object : StringRequest(Method.POST,mycourseurl,com.android.volley.Response.Listener {
            try{
                buy_txt.text="START LEARNING"
//                if(it.equals("success")){
//
//                    Toast.makeText(applicationContext,"Payment Id saved to database", Toast.LENGTH_LONG).show()
//                }
//                else{
//                    //Toast.makeText(applicationContext,"Error While saving payment id", Toast.LENGTH_LONG).show()
//                }
            }
            catch (e:Exception){
                Toast.makeText(applicationContext,"Error Occurred",Toast.LENGTH_SHORT).show()
            }
        },com.android.volley.Response.ErrorListener {
            Toast.makeText(applicationContext, "Volley error occurred!!!", Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params.put("email", sharedPreferences.getString("email","")!!)
                params.put("courseCode", courseCode!!)
                params.put("expiryDate", "")
                params.put("amount", amount!!)
                return params
            }
        }
        queue2.add(jsonObjectRequest2)


    }

    override fun onPaymentError(p0: Int, p1: String?) {
        val dialog=AlertDialog.Builder(this)
        dialog.setMessage(p1)
        dialog.show()
    }
}
