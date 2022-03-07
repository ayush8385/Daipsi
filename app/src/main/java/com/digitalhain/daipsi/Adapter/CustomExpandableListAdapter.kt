package com.digitalhain.daipsi.Adapter

import android.graphics.Typeface

import com.digitalhain.daipsi.R
import android.content.Context

import android.widget.TextView

import android.view.ViewGroup

import android.view.LayoutInflater
import android.view.View

import android.widget.BaseExpandableListAdapter
import com.digitalhain.daipsi.model.Topic


class CustomExpandableListAdapter(
    context: Context, expandableListTitle: List<String>,
    expandableListDetail: Map<String, List<String>>
) : BaseExpandableListAdapter() {
    private val mContext: Context
    private val mExpandableListTitle: List<String>
    private val mExpandableListDetail: Map<String, List<String>>
    private val mLayoutInflater: LayoutInflater


    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return mExpandableListDetail[mExpandableListTitle[listPosition]]!!.get(expandedListPosition)
    }

    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }

    override fun getChildView(
        listPosition: Int, expandedListPosition: Int,
        isLastChild: Boolean, convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        val expandedListText = getChild(listPosition, expandedListPosition) as Topic
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null)
        }
        val expandedListTextView = convertView!!.findViewById(R.id.listTitle) as TextView
        expandedListTextView.text = expandedListText.name
        return convertView
    }

    override fun getChildrenCount(listPosition: Int): Int {
        return mExpandableListDetail[mExpandableListTitle[listPosition]]!!.size
    }

    override fun getGroup(listPosition: Int): Any {
        return mExpandableListTitle[listPosition]
    }

    override fun getGroupCount(): Int {
        return mExpandableListTitle.size
    }

    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }

    override fun getGroupView(
        listPosition: Int, isExpanded: Boolean,
        convertView: View?, parent: ViewGroup?
    ): View? {
        var convertView: View? = convertView
        val listTitle = getGroup(listPosition) as String
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_group, null)
        }
        val listTitleTextView = convertView!!.findViewById(R.id.listTitle) as TextView
        listTitleTextView.setTypeface(null, Typeface.BOLD)
        listTitleTextView.text = listTitle
        return convertView
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }

    init {
        mContext = context
        mExpandableListTitle = expandableListTitle
        mExpandableListDetail = expandableListDetail
        mLayoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}