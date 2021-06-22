package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.R

class HomeAdapter(val context: Context,val items:ArrayList<String>):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var name:TextView=view.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.home_single_row,parent,false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var nam=items[position]
        holder.name.text=nam
    }

    override fun getItemCount(): Int {
        return items.size
    }
}