package com.example.congresotfg.common.utils

import android.content.Context
import android.renderscript.ScriptGroup.Binding
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImageClass {
    fun loadImage(img : String,iv : ImageView,context : Context){
        Glide.with(context)
            .load(img)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(iv)
    }
}