package com.digitalhain.daipsi.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Adapter.HomeAdapter
import com.digitalhain.daipsi.R

class HomeActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: HomeAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    var names= arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView=findViewById(R.id.homerecycler)

        layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        names.add("Jai")
        names.add("Ajay")
        names.add("Hello")
        names.add("Ram")
        names.add("Veeru")

        recyclerAdapter= HomeAdapter(this,names)
        recyclerView.adapter=recyclerAdapter

    }
}