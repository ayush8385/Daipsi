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
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.internal.e




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

        val youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance()

        ytPlayer.initialize("AIzaSyD-S_lp40K-HW-fthWZu4cwxxQJJRxeqpA", object : YouTubePlayer.OnInitializedListener{
            // Implement two methods by clicking on red error bulb
            // inside onInitializationSuccess method
            // add the video link or the
            // playlist link that you want to play
            // In here we also handle the play and pause functionality
            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo("HzeK7g8cD0Y")
                player?.play()
            }

            // Inside onInitializationFailure
            // implement the failure functionality
            // Here we will show toast
            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })

//        val youtubeFrag: YouTubePlayerSupportFragment = supportFragmentManager.findFragmentById(R.id.frag) as YouTubePlayerSupportFragment
//
//        youtubeFrag.initialize("AIzaSyD-S_lp40K-HW-fthWZu4cwxxQJJRxeqpA",object :YouTubePlayer.OnInitializedListener{
//            override fun onInitializationSuccess(
//                provider: YouTubePlayer.Provider?,
//                player: YouTubePlayer?,
//                restored: Boolean
//            ) {
//                if(player==null){
//                    return
//                }
//                if(restored){
//                    player.play()
//                }
//                else{
//                    player.cueVideo("Y0Dj-VCXE_o")
//                    player.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT)
//                }
//            }
//
//            override fun onInitializationFailure(
//                p0: YouTubePlayer.Provider?,
//                p1: YouTubeInitializationResult?
//            ) {
//                TODO("Not yet implemented")
//            }
//
//        })



//        val youTubePlayerView = findViewById<YouTubePlayerView>(R.id.youtube_player_view)
//        lifecycle.addObserver(youTubePlayerView)
//
//        youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
//            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "e20EFLNwHuQ"
//                youTubePlayer.loadVideo(videoId, 0f)
//            }
//        })



//        video.setVideoPath("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerBlazes.mp4")
//        video.start()
//
//        video.setOnPreparedListener {
//            progressbar.visibility= View.GONE
//        }
//
//        toogle.setOnClickListener {
//            requestedOrientation=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//            val param:LinearLayout.LayoutParams = LinearLayout.LayoutParams(100,1200)
//            box.layoutParams=param
//        }



    }
}
