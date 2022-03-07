package com.digitalhain.daipsi.model

import android.os.Parcel
import android.os.Parcelable


data class TopCourses(
    val id:String?,
    val name: String?,
    val code:String?,
    val by:String?,
    val reveiw:String?,
    val image:String?,
    val video:String?,
    val count:String?,
    val desc:String?,
    val req:String?,
    val price:String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(code)
        parcel.writeString(by)
        parcel.writeString(reveiw)
        parcel.writeString(image)
        parcel.writeString(video)
        parcel.writeString(count)
        parcel.writeString(desc)
        parcel.writeString(req)
        parcel.writeString(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TopCourses> {
        override fun createFromParcel(parcel: Parcel): TopCourses {
            return TopCourses(parcel)
        }

        override fun newArray(size: Int): Array<TopCourses?> {
            return arrayOfNulls(size)
        }
    }

}