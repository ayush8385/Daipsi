package com.digitalhain.daipsi.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.digitalhain.daipsi.Activity.SplashActivity
import com.digitalhain.daipsi.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler().postDelayed({
            val i = Intent(this@SplashActivity, GetStartedActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_SCREEN_TIME_OUT.toLong())
    }

    companion object {
        private const val SPLASH_SCREEN_TIME_OUT = 2000
    }
}