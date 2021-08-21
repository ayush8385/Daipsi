package com.digitalhain.daipsi.Activity

import android.content.pm.ActivityInfo
import android.graphics.drawable.GradientDrawable
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.digitalhain.daipsi.R
import java.net.URI
import android.util.DisplayMetrics
import android.widget.*
import androidx.fragment.app.FragmentActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment




class CourseDetail : AppCompatActivity() {
   // lateinit var video:VideoView
    lateinit var progressbar:ProgressBar
    lateinit var toogle:ImageView
    var toogled=false
    lateinit var box:RelativeLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_detail)

       // video=findViewById(R.id.video)
//        progressbar=findViewById(R.id.progressbar)
//        toogle=findViewById(R.id.mark)
        box=findViewById(R.id.box)

//        val ytPlayer:YouTubePlayerSupportFragment = supportFragmentManager.findFragmentById(R.id.youtubesupportfragment) as YouTubePlayerSupportFragment
//
//        ytPlayer.initialize("AIzaSyD-S_lp40K-HW-fthWZu4cwxxQJJRxeqpA", object : YouTubePlayer.OnInitializedListener{
//            // Implement two methods by clicking on red error bulb
//            // inside onInitializationSuccess method
//            // add the video link or the
//            // playlist link that you want to play
//            // In here we also handle the play and pause functionality
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                p2: Boolean
//            ) {
//                player?.loadVideo("HzeK7g8cD0Y")
//                player?.play()
//            }
//
//            // Inside onInitializationFailure
//            // implement the failure functionality
//            // Here we will show toast
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                Toast.makeText(applicationContext , "Video player Failed" , Toast.LENGTH_SHORT).show()
//            }
//        })

        val youtubeFrag: YouTubePlayerSupportFragment = supportFragmentManager.findFragmentById(R.id.youtubesupportfragment) as YouTubePlayerSupportFragment

        youtubeFrag.initialize("AIzaSyD-S_lp40K-HW-fthWZu4cwxxQJJRxeqpA",object :YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                restored: Boolean
            ) {
                if(player==null){
                    return
                }
                if(restored){
                    player.play()
                }
                else{
                    player.loadVideo("0zx_eFyHRU0")
                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
                }
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }

        })



    }
}
