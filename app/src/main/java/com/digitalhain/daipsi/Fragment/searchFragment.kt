package com.digitalhain.daipsi.Fragment

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Activity.ConnectionManager
import com.digitalhain.daipsi.Activity.MainActivity
import com.digitalhain.daipsi.Adapter.SearchAdapter
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Subject
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONException
import pl.droidsonroids.gif.GifImageView


class searchFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter:SearchAdapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var textse:TextView
    lateinit var noDataText:TextView
    lateinit var url:String
    lateinit var searchView:SearchView
    lateinit var gif: GifImageView
    var subjectArray = arrayListOf<Subject>()
    val filteredlist:ArrayList<Subject> = ArrayList()
    var str=""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.activity_searched_item, container, false)

        textse=view.findViewById(R.id.text_ser)
        noDataText=view.findViewById(R.id.noDataText)
        searchView=view.findViewById(R.id.search_bar)
        gif=view.findViewById(R.id.gif)

        val sub= arguments!!.getString("subject")

        if(sub=="Engineering"){
            str="engineering"
        }
        else if(sub=="Medical"){
            str="medical"
        }
        else if(sub=="Commerce"){
            str="commerce"
        }
        else{
            str="govtexams"
        }
        url="http://daipsi.com/api/"+str+".php/"
        val queue= Volley.newRequestQueue(context)

        recyclerView=view.findViewById(R.id.recyclermain)
        layoutManager= LinearLayoutManager(context)

        if(ConnectionManager().checkconnectivity(activity as Context)){
            val stringRequest = StringRequest(
                Request.Method.GET, url,
                { response ->
                    try {
                        //converting the string to json array object
                        val array = JSONArray(response)


                        //traversing through all the object
                        for (i in 0 until array.length()) {

                            //getting product object from json array
                            val product = array.getJSONObject(i)

                            val itemjsonRequest=Subject(
                                product.getString("Question"),
                                product.getString("Answer")
                            )
                            subjectArray.add(itemjsonRequest)

                            //adding the product to product list
                        }


                        recyclerAdapter= SearchAdapter(activity as Context,filteredlist)
                        recyclerView.layoutManager=layoutManager
                        recyclerView.adapter=recyclerAdapter
                        textse.visibility=View.VISIBLE
                        //creating adapter object and setting it to recyclerview


                    } catch (e: JSONException) {
                        Toast.makeText(context,"Error found", Toast.LENGTH_LONG).show()
                    }
                }
            ) { }
            queue.add(stringRequest)
        }
        else{
            Toast.makeText(context,"Not connected to internet", Toast.LENGTH_LONG).show()
        }
        searchElement()
        return view
    }

    private fun searchElement() {
        searchView.queryHint="Search your question..."
        searchView.setIconifiedByDefault(false)
        val searchIcon:ImageView = searchView.findViewById(R.id.search_mag_icon);
        searchIcon.visibility= View.GONE
        searchIcon.setImageDrawable(null)
        val closeIcon:ImageView = searchView.findViewById(R.id.search_close_btn);
        closeIcon.setColorFilter(Color.BLACK)
        val theTextArea = searchView.findViewById<View>(R.id.search_src_text) as SearchView.SearchAutoComplete
        theTextArea.setTextColor(Color.BLACK)
        theTextArea.setHintTextColor(Color.DKGRAY)//or any color that you want
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                filtering(query!!)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterr(newText!!)
                return true
            }
        })
    }
    private fun filtering(text: String) {
        val filtered:ArrayList<Subject> = ArrayList()
        for(item in subjectArray){
            if(item.ques!!.toLowerCase().contains(text!!.toLowerCase()) && text!=""){
                filtered.add(item)
            }
        }
        if(filtered.isEmpty()){
            val queue=Volley.newRequestQueue(context)
            val jsonObjectRequest=object : StringRequest(Method.POST,url,com.android.volley.Response.Listener {
                try{
                    if(it.equals("success")){
                        Toast.makeText(context,"Question Saved to Database", Toast.LENGTH_LONG).show()
                        Log.d("repsonse...",it)
                    }
                    else{
                        Toast.makeText(context,"Error While placing Order", Toast.LENGTH_LONG).show()
                    }
                }
                catch (e:Exception){
                    Toast.makeText(context,"Error Occurred",Toast.LENGTH_SHORT).show()
                }
            },com.android.volley.Response.ErrorListener {
                Toast.makeText(context, "Volley error occurred!!!", Toast.LENGTH_SHORT).show()
            }){
                override fun getParams(): MutableMap<String, String> {
                    val params=HashMap<String,String>()
                    params.put("question",text)
                    params.put("course",str)
                    return params
                }
            }
            queue.add(jsonObjectRequest)
        }
    }
    fun filterr(text:String){
        val filtered:ArrayList<Subject> = ArrayList()
        for(item in subjectArray){
            if(item.ques!!.toLowerCase().contains(text.toLowerCase()) && text!=""){
                filtered.add(item)
            }
        }
        if (filtered.isEmpty()){
            textse.visibility=View.GONE
            // gif.visibility = View.VISIBLE
            noDataText.visibility = View.VISIBLE
            //   Toast.makeText(applicationContext,"No Data found", Toast.LENGTH_SHORT).show()
            recyclerAdapter.filterList(filtered)
        }
        else{
            recyclerAdapter.filterList(filtered)
        }
    }


}