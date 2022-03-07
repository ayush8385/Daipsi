package com.digitalhain.daipsi.Fragment

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.ayush.livesearch.ApiClient
import com.ayush.livesearch.ApiInterface
import com.digitalhain.daipsi.Activity.MainActivity
import com.digitalhain.daipsi.Adapter.SearchAdapter
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Subject
import com.digitalhain.daipsisearch.Activities.Room.QuestionEntity
import com.digitalhain.daipsisearch.Activities.Room.QuestionViewModel
import pl.droidsonroids.gif.GifImageView
import retrofit2.Call
import retrofit2.Callback
import java.util.prefs.Preferences

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFrag.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFrag : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var ll_center: LinearLayout
    lateinit var recyclerAdapter: SearchAdapter
    lateinit var layoutManager: LinearLayoutManager
    lateinit var textse: TextView
    lateinit var noDataText: TextView
    lateinit var url:String
    lateinit var wait: TextView
    lateinit var searchView: SearchView
    lateinit var gif: GifImageView
    lateinit var apiInterface: ApiInterface
    lateinit var subjectArray:ArrayList<Subject>
    lateinit var sharedPreferences: Preferences

    val handler = Handler()
    val filteredlist:ArrayList<Subject> = ArrayList()
    var str=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        ll_center = view.findViewById(R.id.ll_center)
        textse=view.findViewById(R.id.text_ser)
        noDataText=view.findViewById(R.id.noDataText)
        searchView=view.findViewById(R.id.search_bar)
        gif=view.findViewById(R.id.gif)
        wait=view.findViewById(R.id.wait_text)


        recyclerView=view.findViewById(R.id.recyclermain)
        layoutManager=LinearLayoutManager(context as Activity)
        recyclerView.layoutManager=layoutManager
        recyclerView.setHasFixedSize(true)

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

        gif.visibility=View.GONE
        wait.visibility=View.GONE
        searchView.visibility=View.VISIBLE

        searchElement()

        fetchQuestion("","")

        return view
    }


    private fun searchElement() {

        recyclerAdapter= SearchAdapter(activity!!)
        recyclerView.adapter=recyclerAdapter

        searchView.queryHint="Search your question..."
        searchView.setIconifiedByDefault(false)
        val searchIcon: ImageView = searchView.findViewById(R.id.search_mag_icon);
        searchIcon.visibility= View.GONE
        searchIcon.setImageDrawable(null)
        val closeIcon: ImageView = searchView.findViewById(R.id.search_close_btn);
        closeIcon.setColorFilter(Color.BLACK)
        val theTextArea = searchView.findViewById<View>(R.id.search_src_text) as SearchView.SearchAutoComplete
        theTextArea.setTextColor(Color.BLACK)
        theTextArea.setHintTextColor(Color.DKGRAY)//or any color that you want

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()
                if(query.length>1){
                    fetchQuest(query,str)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    if(newText.length>1){
                        fetchQuestion(newText.replace("\\s".toRegex(), "").replace("\\?".toRegex(), ""),str)
                    }
                    else{
                        filteredlist.clear()
                        recyclerAdapter.filterList(filteredlist)
                    }
                }, 500)
                return false

            }

        })

    }

    fun fetchQuest(key:String, course:String){

        apiInterface= ApiClient().getApiClient().create(ApiInterface::class.java)
        val call: Call<List<Subject>> =apiInterface.getQuestions(key.replace("\\s".toRegex(), "").replace("\\?".toRegex(), ""),course)

        call.enqueue(object : Callback<List<Subject>> {
            override fun onResponse(call: Call<List<Subject>>, response: retrofit2.Response<List<Subject>>) {
                textse.visibility=View.VISIBLE
                subjectArray= (response.body() as ArrayList<Subject>?)!!

                recyclerAdapter.filterList(subjectArray as ArrayList<Subject>)

                var m=0
                for(quest in subjectArray){
                    if(quest.ques!!.toLowerCase().contains(key.toLowerCase())){
                        m=1
                        break
                    }
                }

                if(m==0){
                    askQuestion(str,key)
                }
            }

            override fun onFailure(call: Call<List<Subject>>, t: Throwable) {
                gif.visibility=View.GONE
                wait.visibility=View.GONE
                searchView.visibility=View.VISIBLE


                Toast.makeText(activity!!,"Error on :"+t.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    fun askQuestion(str: String, newText: String) {

        url="https://daipsi.com/Android_App_Daipsi/"+str+".php/"
        val queue= Volley.newRequestQueue(context as Activity)

        val jsonObjectRequest=object : StringRequest(Method.POST,url, Response.Listener {
            try{
                if(it.equals("success")){

//                        sharedPreferences.addQuestion(applicationContext,Subject(str,newText))

                    QuestionViewModel(activity!!.application).inserQuestion(QuestionEntity(str,newText))


                    Toast.makeText(activity!!,"Your Answer will be available in next 60 mins", Toast.LENGTH_LONG).show()
                    Log.d("repsonse...",it)
                }
                else{
                    Toast.makeText(activity!!,"Error While Saving Question", Toast.LENGTH_LONG).show()
                }
            }
            catch (e:Exception){
                Toast.makeText(activity!!,"Error Occurred", Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener {
            Toast.makeText(activity!!, "Volley error occurred!!!", Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params.put("question",newText)
                params.put("course",str)
                params.put("asked_by", activity!!.getSharedPreferences("Shared Preference",Context.MODE_PRIVATE).getString("fcm_token","")!!)
                return params
            }
        }
        queue.add(jsonObjectRequest)
    }

    fun fetchQuestion(key:String, course:String){

        apiInterface= ApiClient().getApiClient().create(ApiInterface::class.java)
        val call: Call<List<Subject>> =apiInterface.getQuestions(key,course)

        call.enqueue(object : Callback<List<Subject>> {
            override fun onResponse(call: Call<List<Subject>>, response: retrofit2.Response<List<Subject>>) {

                subjectArray= (response.body() as ArrayList<Subject>?)!!


                recyclerAdapter.filterList(subjectArray)


            }

            override fun onFailure(call: Call<List<Subject>>, t: Throwable) {
                gif.visibility=View.GONE
                wait.visibility=View.GONE
                searchView.visibility=View.VISIBLE

                Toast.makeText(activity!!,"Error on :"+t.toString(), Toast.LENGTH_LONG).show()
            }

        })

    }


}