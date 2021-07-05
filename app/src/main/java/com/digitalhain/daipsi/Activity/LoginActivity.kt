package com.digitalhain.daipsi.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.R

class LoginActivity : AppCompatActivity() {
    lateinit var email: EditText
    lateinit var password: EditText
    lateinit var login: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email = findViewById(R.id.login_email)
        password = findViewById(R.id.login_password)
        login = findViewById(R.id.login)

        login.setOnClickListener {
           logIn()
        }
    }

    private fun logIn() {
        val etemail=email.text.toString().trim()
        val etpass=password.text.toString().trim()

        val url="http://daipsi.com/api/login.php/"
        val queue= Volley.newRequestQueue(this)

        if(!etemail.equals("") && !etpass.equals("")){
            val stringRequest=object : StringRequest(Method.POST,url, Response.Listener {
                try{
                    if(it.equals("success")){
                        Toast.makeText(this,"Login Successfully", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,MainActivity::class.java))
                    }
                    else{
                        Toast.makeText(this,"Invalid Login Id/Password", Toast.LENGTH_LONG).show()
                    }
                }
                catch (e:Exception){
                    Toast.makeText(applicationContext,"Error Occurred", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
            }){
                override fun getParams(): MutableMap<String, String> {
                    val params=HashMap<String,String>()
                    params.put("email",etemail)
                    params.put("password",etpass)
                    return params
                }
            }
            queue.add(stringRequest)
        }
        else{
            Toast.makeText(applicationContext,"Enter Proper Details", Toast.LENGTH_SHORT).show()
        }
    }

    fun goto_create_account(view: View?) {
        val intent = Intent(this@LoginActivity, createAccountActivity::class.java)
        startActivity(intent)
        finish()
    }
}