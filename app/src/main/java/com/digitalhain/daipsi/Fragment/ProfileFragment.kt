package com.digitalhain.daipsi.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import com.digitalhain.daipsi.Fragment.ProfileFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Activity.ConnectionManager
import com.digitalhain.daipsi.Adapter.CourseAdapter
import com.digitalhain.daipsi.Adapter.MyCourseAdapter
import com.digitalhain.daipsi.Adapter.TopicAdapter
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.TopCourses
import com.digitalhain.daipsi.model.Topic
import org.json.JSONArray
import org.json.JSONException

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {

    lateinit var name:TextView
    lateinit var mail:TextView
    lateinit var number:TextView
    lateinit var sharedPreferences: SharedPreferences
    var course_list= arrayListOf<TopCourses>()
    lateinit var mycoursrecycler:RecyclerView
    lateinit var mycoursadapter:MyCourseAdapter
    lateinit var layoutManager:RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        name=view.findViewById(R.id.user_name)
        mail=view.findViewById(R.id.user_mail)
        number=view.findViewById(R.id.user_num)
        mycoursrecycler=view.findViewById(R.id.course_recycler)

        sharedPreferences=activity!!.getSharedPreferences("Shared Preference", Context.MODE_PRIVATE)

        name.text=sharedPreferences.getString("name","")
        mail.text=sharedPreferences.getString("email","")
        number.text=sharedPreferences.getString("number","")

        loadmyCourses()

        return view
    }

    private fun loadmyCourses() {
        val url="https://daipsi.com/Android_Daipsi/MyCourses/retrieve.php/"
        val queue= Volley.newRequestQueue(context)

        val stringRequest_topic=object :StringRequest(Method.POST,url, Response.Listener {
            try{
                val array = JSONArray(it)
                // var childitem = ArrayList<Topic>()
                for (j in 0 until array.length()) {
                    val product = array.getJSONObject(j)

                    val itemjsonRequest= TopCourses(
                        product.getString("id"),
                        product.getString("C_Name"),
                        product.getString("C_Code"),
                        product.getString("C_By"),
                        product.getString("C_Review"),
                        product.getString("C_Image"),
                        product.getString("C_Video"),
                        product.getString("C_No"),
                        product.getString("C_Des"),
                        product.getString("C_Requirement"),
                        product.getString("C_Price")
                    )
                    course_list.add(itemjsonRequest)

                }
                mycoursadapter= MyCourseAdapter(activity as Context,course_list)
                mycoursrecycler.adapter=mycoursadapter
                layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                mycoursrecycler.layoutManager=layoutManager


            }
            catch (e:Exception){
                Toast.makeText(context,e.toString(),Toast.LENGTH_SHORT).show()
            }
        }, Response.ErrorListener {
            Toast.makeText(context,it.toString(), Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()
                params.put("email", sharedPreferences.getString("email","")!!)
                return params
            }
        }
        queue.add(stringRequest_topic)

    }

}