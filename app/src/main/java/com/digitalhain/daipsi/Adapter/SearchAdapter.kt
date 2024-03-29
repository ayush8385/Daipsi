package com.digitalhain.daipsi.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Activity.AnswerView
import com.digitalhain.daipsi.Fragment.SearchFrag
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Subject

class SearchAdapter(val context: Context) : RecyclerView.Adapter<SearchAdapter.MainViewHolder>(){
    private var itemList:ArrayList<Subject> = arrayListOf<Subject>()
    class MainViewHolder(view: View): RecyclerView.ViewHolder(view){
        val question: TextView =view.findViewById(R.id.question)
        val answer: TextView =view.findViewById(R.id.answer)
        val parent:LinearLayout = view.findViewById(R.id.parent)

    }

    fun filterList(filterlist:ArrayList<Subject>){
        itemList.clear()
        itemList.addAll(filterlist)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchAdapter.MainViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_res_search,parent,false)

        return SearchAdapter.MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item=itemList[position]
        holder.question.text=item.ques
        holder.answer.text=item.ans

        holder.parent.setOnClickListener{
            val intent = Intent(context,AnswerView::class.java)
            intent.putExtra("course","")
            intent.putExtra("Ques",item.ques)
            intent.putExtra("Ans",item.ans)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return itemList.size
    }
}