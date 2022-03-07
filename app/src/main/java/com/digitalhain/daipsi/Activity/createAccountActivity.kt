package com.digitalhain.daipsi.Activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.R
import org.json.JSONArray
import org.json.JSONException

class createAccountActivity : AppCompatActivity() {
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var signup:LinearLayout
    lateinit var name:EditText
    lateinit var qualif:EditText
    lateinit var number:EditText
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        email=findViewById(R.id.signup_email)
        password=findViewById(R.id.signup_password)
        signup=findViewById(R.id.signup)
        name=findViewById(R.id.signup_name)
        qualif=findViewById(R.id.signup_qual)
        number=findViewById(R.id.signup_number)

        sharedPreferences=getSharedPreferences("Shared Preference", Context.MODE_PRIVATE)

        signup.setOnClickListener {
            signUp()
        }


    }

    private fun signUp() {
        val etemail=email.text.toString().trim()
        val etpass=password.text.toString().trim()

        val url="https://daipsi.com/Android_Daipsi/LoginAuth/register.php/"
        val queue= Volley.newRequestQueue(this)

        if(etpass.length <=4){
            Toast.makeText(applicationContext,"Password is too short",Toast.LENGTH_SHORT).show()
            return
        }

        if(!etemail.equals("") && !etpass.equals("")){
            val stringRequest=object : StringRequest(Method.POST,url, Response.Listener {
                try{
                    if(it.equals("success")){
                        Toast.makeText(this,"Registered Successfully", Toast.LENGTH_LONG).show()
                        sharedPreferences.edit().putString("name",name.text.toString()).apply()
                        sharedPreferences.edit().putString("email",etemail).apply()
                        sharedPreferences.edit().putString("number",number.text.toString()).apply()
                        sharedPreferences.edit().putString("password",etpass).apply()
                        sharedPreferences.edit().putBoolean("isLoggedIn",true).apply()
                        startActivity(Intent(this,MainActivity::class.java))
                        finishAffinity()
                    }
                    else if(it.equals("Already")){
                        Toast.makeText(this,"Already Registered Please Login", Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(this,"Error While Registering", Toast.LENGTH_LONG).show()
                    }
                }
                catch (e:Exception){
                    Toast.makeText(applicationContext,"Error Occurred",Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }){
                override fun getParams(): MutableMap<String, String> {
                    val params=HashMap<String,String>()
                    params.put("name",name.text.toString())
                    params.put("email",etemail)
                    params.put("password",etpass)
                    params.put("number",number.text.toString())
                    params.put("qualification",qualif.text.toString())
                    return params
                }
            }
            queue.add(stringRequest)
        }
        else{
            Toast.makeText(applicationContext,"Enter Proper Details",Toast.LENGTH_SHORT).show()
        }
    }

    fun goto_login(view: View?) {
        val intent = Intent(this@createAccountActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}