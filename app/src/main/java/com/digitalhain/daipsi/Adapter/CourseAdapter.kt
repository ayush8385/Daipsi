package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Activity.CourseDetail
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items
import com.digitalhain.daipsi.model.TopCourses
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.*

class CourseAdapter(val context: Context, val items: ArrayList<TopCourses>):RecyclerView.Adapter<CourseAdapter.HomeViewHolder>() {
    class HomeViewHolder(val view: View):RecyclerView.ViewHolder(view){
        var by:TextView=view.findViewById(R.id.course_by)
        var name:TextView=view.findViewById(R.id.course_name)
        var image: ImageView =view.findViewById(R.id.image)
        var course_detail:LinearLayout=view.findViewById(R.id.course)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.courses_single_row,parent,false)

        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        var item=items[position]
        holder.name.text=item.name
        holder.by.text=item.by
        Picasso.get().load("https://daipsi.com/Android_Daipsi/TopCourses/images/"+item.image).into(holder.image)

        holder.course_detail.setOnClickListener {
            val intent = Intent(context,CourseDetail::class.java)
            intent.putExtra("c_det",item)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}