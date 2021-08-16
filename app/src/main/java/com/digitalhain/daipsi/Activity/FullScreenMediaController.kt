package com.digitalhain.daipsi.Activity

import android.app.Activity

import android.content.Intent

import android.R
import android.content.Context

import android.view.Gravity
import android.view.View

import android.widget.FrameLayout

import android.widget.ImageButton
import android.widget.MediaController


class FullScreenMediaController(context: Context?) : MediaController(context) {
    private var fullScreen: ImageButton? = null
    private var isFullScreen: String? = null
    override fun setAnchorView(view: View?) {
        super.setAnchorView(view)

        //image button for full screen to be added to media controller
        fullScreen = ImageButton(super.getContext())
        val params: FrameLayout.LayoutParams =
            FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        params.gravity = Gravity.CENTER
        addView(fullScreen, params)

        //fullscreen indicator from intent
        isFullScreen = (getContext() as Activity).intent.getStringExtra("fullScreenInd")
        if ("y" == isFullScreen) {
            fullScreen!!.setImageResource(com.digitalhain.daipsi.R.drawable.arrow)
        } else {
            fullScreen!!.setImageResource(com.digitalhain.daipsi.R.drawable.arrow)
        }

        //add listener to image button to handle full screen and exit full screen events
        fullScreen!!.setOnClickListener(object : OnClickListener {
            override fun onClick(v: View?) {
                val intent = Intent(getContext(), CourseDetail::class.java)
                if ("y" == isFullScreen) {
                    intent.putExtra("fullScreenInd", "")
                } else {
                    intent.putExtra("fullScreenInd", "y")
                }
                (getContext() as Activity).startActivity(intent)
            }
        })
    }
}