package com.digitalhain.daipsi.Activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.digitalhain.daipsi.Fragment.*
import com.digitalhain.daipsi.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    var bottomNavigationView: BottomNavigationView? = null
    lateinit var frameLayout:FrameLayout
    lateinit var navigationView:BottomNavigationView
    var previousMenuItem:MenuItem?=null
    var sub=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        frameLayout=findViewById(R.id.main_frameLayout)
        navigationView=findViewById(R.id.bottomNavigationView)

        openHome()


        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.featured ->{
                    openHome()
                    it.isCheckable=true
                    it.isChecked=true
                }
                R.id.search ->{
                    selectSub()
                    val bundle = Bundle()
                    bundle.putString("subject",sub)
                    val fragment=searchFragment()
                    fragment.arguments=bundle
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout,
                        fragment).commit()
                }
                R.id.mentorship ->{
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout,
                        personalMentorshipFragment()).commit()
                }
                R.id.consult ->{
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout,
                        ConsultancyFragment()).commit()
                }
                R.id.account ->{
                    supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout,
                        ProfileFragment()).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun selectSub() {
        lateinit var dialog:AlertDialog
        val array = arrayOf("Medical","Engineering","Commerce","Government Exam")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Subject to Continue")
        builder.setSingleChoiceItems(array,-1,{_,which->
            sub = array[which]
            dialog.dismiss()

        })
        dialog = builder.create()
        dialog.show()
    }

    override fun onBackPressed() {
        val frag=supportFragmentManager.findFragmentById(R.id.main_frameLayout)
        when(frag){
            !is featuredFragment -> openHome()
            else->opendialog()
        }
    }

    private fun opendialog() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to exit?")
            .setCancelable(false)
            .setTitle("Exit Alert?")
            .setPositiveButton("Yes") { dialog, id -> super@MainActivity.onBackPressed() }
            .setNegativeButton("No") { dialog, id -> dialog.cancel() }
        val alert = builder.create()
        alert.show()
    }

    fun openHome(){
        supportFragmentManager.beginTransaction().replace(R.id.main_frameLayout,
            featuredFragment()).commit()
        navigationView.menu.findItem(R.id.featured).setCheckable(true)
        navigationView.menu.findItem(R.id.featured).setChecked(true)
    }
}