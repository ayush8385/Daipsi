package com.digitalhain.daipsi.model

import android.os.Parcel
import android.os.Parcelable

data class Topic(
    val id:String?,
    val course_id:String,
    val topic_id:String,
    val name: String?,
    val video:String?,
    val desc:String?
)