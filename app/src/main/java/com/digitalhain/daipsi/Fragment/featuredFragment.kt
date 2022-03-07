package com.digitalhain.daipsi.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Activity.ConnectionManager
import com.digitalhain.daipsi.Adapter.*
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items
import com.digitalhain.daipsi.model.Subject
import com.digitalhain.daipsi.model.TopCourses
import org.json.JSONArray
import org.json.JSONException

class featuredFragment : androidx.fragment.app.Fragment() {
    lateinit var features:RecyclerView
    lateinit var courses:RecyclerView
    lateinit var videos:RecyclerView
    lateinit var partners:RecyclerView
    lateinit var featureAdapter: FeatureAdapter
    lateinit var courseAdapter: CourseAdapter
    lateinit var videosAdapter: VideoAdapter
    lateinit var partnerAdapter: PartnerAdapter
    lateinit var layoutManager:RecyclerView.LayoutManager

    var feature_list= arrayListOf<String>()
    var course_list= arrayListOf<TopCourses>()
    var videos_list= arrayListOf<Items>()
    var partner_list= arrayListOf<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var obj2=Items()
        obj2.course="fshkfgbhsdf"
        obj2.name="dhsgb ndsbh"
        obj2.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        videos_list.add(obj2)
        obj2.course="fshkfgbhsdf"
        obj2.name="dhsgb ndsbh"
        obj2.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        videos_list.add(obj2)
        obj2.course="fshkfgbhsdf"
        obj2.name="dhsgb ndsbh"
        obj2.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        videos_list.add(obj2)
        obj2.course="fshkfgbhsdf"
        obj2.name="dhsgb ndsbh"
        obj2.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        videos_list.add(obj2)
        obj2.course="fshkfgbhsdf"
        obj2.name="dhsgb ndsbh"
        obj2.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        videos_list.add(obj2)

        var obj3=Items()
        obj3.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        partner_list.add(obj3)
        obj3.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        partner_list.add(obj3)
        obj3.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        partner_list.add(obj3)
        obj3.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        partner_list.add(obj3)
        obj3.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        partner_list.add(obj3)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_featured, container, false)

        features=view!!.findViewById(R.id.feature_recycler)
        courses=view!!.findViewById(R.id.course_recycler)
        videos=view!!.findViewById(R.id.videos_recycler)
        partners=view!!.findViewById(R.id.partner_recycler)

        loadSlider()

        loadTopCourses()



        videosAdapter= VideoAdapter(activity as Context,videos_list)
        videos.adapter=videosAdapter
        layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        videos.layoutManager=layoutManager

        partnerAdapter= PartnerAdapter(activity as Context,partner_list)
        partners.adapter=partnerAdapter
        layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        partners.layoutManager=layoutManager

        return view
    }

    private fun loadTopCourses() {
        val url="https://daipsi.com/Android_Daipsi/TopCourses/json_user_fetch.php/"
        val queue= Volley.newRequestQueue(context)

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
                            courseAdapter= CourseAdapter(activity as Context,course_list)
                            courses.adapter=courseAdapter
                            layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                            courses.layoutManager=layoutManager

                            //adding the product to product list
                        }

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
    }


    fun loadSlider(){

        val url="https://daipsi.com/Android_Daipsi/Slider/retrieve.php/"
        val queue= Volley.newRequestQueue(context)

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

                          //  Toast.makeText(activity,product.getString("image"),Toast.LENGTH_LONG).show()

                            feature_list.add(product.getString("image"))
                            featureAdapter=FeatureAdapter(activity as Context,feature_list)
                            features.adapter=featureAdapter
                            layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
                            features.layoutManager=layoutManager

                            //adding the product to product list
                        }

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
    }
}