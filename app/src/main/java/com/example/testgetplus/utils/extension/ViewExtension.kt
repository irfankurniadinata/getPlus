package com.example.testgetplus.utils.extension

import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*

fun ImageView.setImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .into(this)
}

fun RecyclerView.setupLinearLayoutManager(@RecyclerView.Orientation orientation: Int){
    layoutManager = LinearLayoutManager(context, orientation, false)
}