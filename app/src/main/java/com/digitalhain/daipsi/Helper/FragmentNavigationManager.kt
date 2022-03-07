package com.digitalhain.daipsi.Helper

import androidx.fragment.app.FragmentManager
import com.digitalhain.daipsi.Activity.StartLearning
import com.digitalhain.daipsi.Interface.NavigationManager
import android.R

import android.annotation.SuppressLint

import com.digitalhain.daipsi.Activity.MainActivity




class FragmentNavigationManager:NavigationManager() {

    private var sInstance: FragmentNavigationManager? = null

    private var mFragmentManager: FragmentManager? = null
    var mActivity: StartLearning? = null

    fun obtain(activity: StartLearning): FragmentNavigationManager? {
        if (sInstance == null) {
            sInstance = FragmentNavigationManager()
        }
        sInstance!!.configure(activity)
        return sInstance
    }

    private fun configure(activity: StartLearning) {
        mActivity = activity
        mFragmentManager = mActivity!!.supportFragmentManager
    }



//    private fun showFragment(fragment: Fragment, allowStateLoss: Boolean) {
//        val fm = mFragmentManager
//        @SuppressLint("CommitTransaction") val ft: FragmentTransaction = fm!!.beginTransaction()
//            .replace(R.id.container, fragment)
//        ft.addToBackStack(null)
//        if (allowStateLoss || !BuildConfig.DEBUG) {
//            ft.commitAllowingStateLoss()
//        } else {
//            ft.commit()
//        }
//        fm.executePendingTransactions()
//    }

}