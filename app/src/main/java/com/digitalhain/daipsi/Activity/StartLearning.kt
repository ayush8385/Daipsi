package com.digitalhain.daipsi.Activity

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.digitalhain.daipsi.Adapter.TopicAdapter
import com.digitalhain.daipsi.Helper.FragmentNavigationManager
import com.digitalhain.daipsi.Interface.NavigationManager
import com.digitalhain.daipsi.R
import com.digitalhain.daipsi.model.Topic
import org.json.JSONArray
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StartLearning : AppCompatActivity() {

    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mDrawerToggle: ActionBarDrawerToggle
    lateinit var mActivityTitle:String
  //  var myplayer:YouTubePlayer?=null
    var items = arrayListOf<String>()

    var chapter_list = ArrayList<String>()

    lateinit var btn:ImageView
    lateinit var texttitle:TextView
    lateinit var description: TextView
//    lateinit var expandableListView: ExpandableListView
    lateinit var adapter:ExpandableListAdapter
    var lstTitle = arrayListOf<String>()
    var lstChild:Map<String,List<String>> = HashMap()
    lateinit var navigationManager: NavigationManager
//    lateinit var youtubeFrag: YouTubePlayerSupportFragment
    lateinit var fullscreen:ImageView
    lateinit var course_id:String
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var recyclerView: RecyclerView
    lateinit var recycleradapter: TopicAdapter
    var childitem= arrayListOf<Topic>()
    lateinit var topicid:String
    var topic_no:Int=0
    lateinit var player:VideoView
    lateinit var startquiz:AppCompatButton
    lateinit var mediaC:MediaController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_learning)

        mDrawerLayout=findViewById(R.id.drawer_layout)
        mActivityTitle=title.toString()

        btn=findViewById(R.id.menu)
        description=findViewById(R.id.desc)
        texttitle=findViewById(R.id.title_txt)
        recyclerView=findViewById(R.id.drawer_recycler)
        layoutManager=LinearLayoutManager(this)
        startquiz=findViewById(R.id.start_quiz)
        player=findViewById(R.id.videolayout)
//        expandableListView=findViewById(R.id.navList)
        navigationManager= FragmentNavigationManager()
     //   val listHeaderView:View = layoutInflater.inflate(R.layout.nav_header,null,false)
    //    expandableListView.addHeaderView(listHeaderView)

        texttitle.text=intent.getStringExtra("Course_name")
        course_id= intent.getStringExtra("Course_id")!!
        topic_no=intent.getIntExtra("topic_no",0)





        genData(course_id)




        //selectFirstItemAsDefault()

        btn.setOnClickListener {
            if(!mDrawerLayout.isDrawerOpen(Gravity.START)) mDrawerLayout.openDrawer(Gravity.START);
            else mDrawerLayout.closeDrawer(Gravity.END);
        }

        startquiz.setOnClickListener {

            if(startquiz.text == "Start Quiz" && topicid!=""){
                startActivity(Intent(this,Startquiz::class.java).putExtra("topicid",topicid).putExtra("topic_name",texttitle.text).putExtra("topic_no",topic_no))
            }

        }
    }


    private fun genData(courseid:String) {

        val url_topic="https://daipsi.com/Android_Daipsi/Topics/retrive.php/"
        val queue_topic= Volley.newRequestQueue(this)


        val stringRequest_topic=object :StringRequest(Method.POST,url_topic,Response.Listener {
            try{
                val array = JSONArray(it)
               // var childitem = ArrayList<Topic>()
                for (j in 0 until array.length()) {
                    val product = array.getJSONObject(j)

                    val itemJsonRequest = Topic(
                        product.getString("id"),
                        product.getString("course_code"),
                        product.getString("topic_id"),
                        product.getString("topic_name"),
                        product.getString("topic_vdo"),
                        product.getString("topic_desc")
                    )
                    childitem.add(itemJsonRequest)

                }
                recycleradapter=TopicAdapter(this,childitem,object :TopicAdapter.OnclickListener{
                    override fun loadTopic(item: Topic) {
                        texttitle.text=item.name
                        description.text=item.desc
                        topicid=item.topic_id
                        mDrawerLayout.closeDrawers()

                        mediaC= MediaController(this@StartLearning)
                        mediaC.setAnchorView(player)
                        player.setMediaController(mediaC)

                        player.setVideoURI(Uri.parse("https://daipsi.com/Android_Daipsi/TopicVideos/" + item.video))
                        player.requestFocus()
                        player.start()
                    }

                })
                recyclerView.adapter=recycleradapter
                recyclerView.layoutManager=layoutManager

                openDefaultLayout(childitem[topic_no])

                mDrawerToggle= ActionBarDrawerToggle(this,mDrawerLayout,R.string.drawer_open,R.string.drawer_close)
                mDrawerLayout.setDrawerListener(mDrawerToggle)


            }
            catch (e:Exception){
                Toast.makeText(applicationContext,e.toString(),Toast.LENGTH_SHORT).show()
            }
        },Response.ErrorListener {
            Toast.makeText(this,it.toString(), Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params=HashMap<String,String>()

                params.put("course",courseid)
                return params
            }
        }
        queue_topic.add(stringRequest_topic)

    }

    fun openDefaultLayout(topic: Topic) {
        texttitle.text=topic.name
        description.text=topic.desc
        topicid=topic.topic_id

        mediaC=MediaController(this@StartLearning)

        mediaC.setAnchorView(player)
        player.setMediaController(mediaC)

        player.setVideoURI(Uri.parse("https://daipsi.com/Android_Daipsi/TopicVideos/" + topic.video))
        player.requestFocus()
        player.start()
    }


//    private fun addDrawersItem() {
//        adapter=CustomExpandableListAdapter(this,lstTitle,lstChild)
//        expandableListView.setAdapter(adapter)
//
//        expandableListView.setOnGroupExpandListener(object :ExpandableListView.OnGroupExpandListener{
//            override fun onGroupExpand(groupPosition: Int) {
//                // supportActionBar!!.title=lstTitle.get(groupPosition).toString()
//            }
//
//        })
//
//        expandableListView.setOnGroupCollapseListener(object :ExpandableListView.OnGroupCollapseListener{
//            override fun onGroupCollapse(groupPosition: Int) {
//                // supportActionBar!!.title="Hwy Hywllo"
//            }
//
//        })
//
//        expandableListView.setOnChildClickListener(object :ExpandableListView.OnChildClickListener{
//            override fun onChildClick(
//                parent: ExpandableListView?,
//                v: View?,
//                groupPosition: Int,
//                childPosition: Int,
//                id: Long
//            ): Boolean {
//
//                val selectedItem = lstChild.get(lstTitle.get(groupPosition))!!.get(childPosition) as Topic
//
//                texttitle.text=selectedItem.name
//                description.text=selectedItem.desc
//
//                mDrawerLayout.closeDrawer(GravityCompat.START)
//
////                if(myplayer!=null){
////                    myplayer!!.release()
////                }
//
////                youtubeFrag = supportFragmentManager.findFragmentById(R.id.youtubesupportfragment) as YouTubePlayerSupportFragment
////                youtubeFrag.initialize("AIzaSyD-S_lp40K-HW-fthWZu4cwxxQJJRxeqpA",object : YouTubePlayer.OnInitializedListener{
////                    override fun onInitializationSuccess(
////                        provider: YouTubePlayer.Provider?,
////                        player: YouTubePlayer?,
////                        restored: Boolean
////                    ) {
////                        myplayer= player!!
////                        if(myplayer==null){
////                            return
////                        }
////                        if(restored){
////                            myplayer!!.play()
////                        }
////                        else{
////                            myplayer!!.loadVideo(selectedItem)
////                            myplayer!!.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
////                        }
////                    }
////
////                    override fun onInitializationFailure(
////                        p0: YouTubePlayer.Provider?,
////                        p1: YouTubeInitializationResult?
////                    ) {
////
////                    }
////
////                })
//
//
//                return true
//            }
//
//        })
//    }

    private fun setUpdrawer() {
        mDrawerToggle = object:ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close){
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                //   supportActionBar!!.title="Hwy Hywllo"
                invalidateOptionsMenu()
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                //    supportActionBar!!.title=mActivityTitle
                invalidateOptionsMenu()
            }
        }

        mDrawerToggle.isDrawerIndicatorEnabled=true
        mDrawerLayout.setDrawerListener(mDrawerToggle)

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setUpdrawer()
        mDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        mDrawerToggle.onConfigurationChanged(newConfig)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        val extras:Extras = applicationContext as Extras
        Toast.makeText(applicationContext,extras.getpassed().toString(),Toast.LENGTH_SHORT).show()
        if(extras.getpassed()){
            openDefaultLayout(childitem[topic_no+1])
            extras.setpasseed(false)
        }
    }
}