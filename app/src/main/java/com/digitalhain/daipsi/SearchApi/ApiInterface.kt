package com.ayush.livesearch

import com.digitalhain.daipsi.model.Subject
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*

interface ApiInterface {
    @GET("demo.php")
    fun getQuestions(@Query("key") keyword:String,@Query("course") course:String):Call<List<Subject>>

    @POST("demo.php")
    fun sendQuestion(@Query("course") course: String,@Query("question") question:String,@Query("askedby") askedby:String,callback: Callback<Response>)


}