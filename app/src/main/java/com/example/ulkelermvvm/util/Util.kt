package com.example.ulkelermvvm.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.ulkelermvvm.R

fun ImageView.downloadAPI(url:String?,plcProgresbar:CircularProgressDrawable){

    val op=RequestOptions().placeholder(plcProgresbar).error(R.drawable.ic_android_black_24dp)

    Glide.with(context).setDefaultRequestOptions(op).load(url).into(this)
}

fun plcProgresbar(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}