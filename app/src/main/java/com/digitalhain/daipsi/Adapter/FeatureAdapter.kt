package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items
import com.squareup.picasso.Picasso
import java.util.*

class FeatureAdapter(val context: Context, val items: ArrayList<Items>):RecyclerView.Adapter<FeatureAdapter.HomeViewHolder>() {
    class HomeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var image: ImageView =view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.features_single_row,parent,false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var item=items[position]
        Picasso.get().load(item.image).into(holder.image)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}