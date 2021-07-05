package com.digitalhain.daipsi.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.digitalhain.daipsi.Adapter.CourseAdapter
import com.digitalhain.daipsi.Adapter.FeatureAdapter
import com.digitalhain.daipsi.Adapter.PartnerAdapter
import com.digitalhain.daipsi.Adapter.VideoAdapter
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Items

class featuredFragment : Fragment() {
    lateinit var features:RecyclerView
    lateinit var courses:RecyclerView
    lateinit var videos:RecyclerView
    lateinit var partners:RecyclerView
    lateinit var featureAdapter: FeatureAdapter
    lateinit var courseAdapter: CourseAdapter
    lateinit var videosAdapter: VideoAdapter
    lateinit var partnerAdapter: PartnerAdapter
    lateinit var layoutManager:RecyclerView.LayoutManager

    var feature_list= arrayListOf<Items>()
    var course_list= arrayListOf<Items>()
    var videos_list= arrayListOf<Items>()
    var partner_list= arrayListOf<Items>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var obj=Items()
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)
        obj.course="fshkfgbhsdf"
        obj.name="dhsgb ndsbh"
        obj.image="https://upload.wikimedia.org/wikipedia/commons/b/b6/Image_created_with_a_mobile_phone.png"
        course_list.add(obj)


        var obj1=Items()
        obj1.image="https://www.orfonline.org/wp-content/uploads/2020/12/Education-1280x720.jpg"
        feature_list.add(obj1)
        obj1.image="https://www.orfonline.org/wp-content/uploads/2020/12/Education-1280x720.jpg"
        feature_list.add(obj1)
        obj1.image="https://www.orfonline.org/wp-content/uploads/2020/12/Education-1280x720.jpg"
        feature_list.add(obj1)
        obj1.image="https://www.orfonline.org/wp-content/uploads/2020/12/Education-1280x720.jpg"
        feature_list.add(obj1)
        obj1.image="https://www.orfonline.org/wp-content/uploads/2020/12/Education-1280x720.jpg"
        feature_list.add(obj1)

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

        featureAdapter=FeatureAdapter(activity as Context,feature_list)
        features.adapter=featureAdapter
        layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        features.layoutManager=layoutManager

        courseAdapter= CourseAdapter(activity as Context,course_list)
        courses.adapter=courseAdapter
        layoutManager= LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        courses.layoutManager=layoutManager

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
}