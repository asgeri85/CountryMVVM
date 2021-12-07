package com.example.ulkelermvvm.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSP {
    companion object{
        private var sp:SharedPreferences?=null

        @Volatile private var instance:CustomSP?=null
        private val lock=Any()

        operator fun invoke(context: Context):CustomSP= instance ?: synchronized(lock){
            instance?: createCustomSP(context).also {
                instance=it
            }
        }

        private fun createCustomSP(context: Context):CustomSP{
            sp=PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSP()
        }
    }

    fun saveTime(time:Long){
        sp?.edit(commit = true){
            putLong("pr_time",time)
        }
    }

    fun getTime()= sp?.getLong("pr_time",0)

}