package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Activity.CourseDetail
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items
import com.digitalhain.daipsi.model.TopCourses
import com.digitalhain.daipsi.model.Topic
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.*

class TopicAdapter(val context: Context, val items: ArrayList<Topic>,val listener:TopicAdapter.OnclickListener):RecyclerView.Adapter<TopicAdapter.HomeViewHolder>() {
    class HomeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        val topic_name:TextView = view.findViewById(R.id.listitem)
        val parent:LinearLayout=view.findViewById(R.id.parent)
        val numbering:TextView=view.findViewById(R.id.numbering)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var item=items[position]
        holder.numbering.text="${position+1}. "
        holder.topic_name.text=item.name

        holder.parent.setOnClickListener{
            listener.loadTopic(item)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnclickListener{
        fun loadTopic(items:Topic)
    }
}