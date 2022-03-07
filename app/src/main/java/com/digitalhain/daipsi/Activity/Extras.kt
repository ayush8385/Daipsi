package com.digitalhain.daipsi.Activity

import android.app.Application
import com.digitalhain.daipsi.model.Topic

class Extras:Application() {
    public var currentTopicPassed:Boolean = false

    var childtopics = arrayListOf<Topic>()

    fun setpasseed(passed:Boolean){
        currentTopicPassed=passed
    }
    fun getpassed():Boolean
    {
        return currentTopicPassed
    }

    fun addTopic(topic:Topic){
        childtopics.add(topic)
    }

    fun getTopic(topic_no:Int):Topic{
        return childtopics[topic_no]
    }

    fun getAllTopic():ArrayList<Topic>{
        return childtopics
    }

}