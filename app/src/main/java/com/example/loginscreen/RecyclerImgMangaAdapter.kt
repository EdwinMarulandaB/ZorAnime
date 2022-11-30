package com.example.loginscreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerImgMangaAdapter (contexActivity:AppCompatActivity, val info:Bundle): RecyclerView.Adapter<RecyclerImgMangaAdapter.MyViewHolder>() {
    class MyViewHolder(var layout:View):RecyclerView.ViewHolder(layout)


    private var contexto:AppCompatActivity = contexActivity

    var myAnimeURL:ArrayList<String> = info.getStringArrayList("url") as ArrayList<String>




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.manga_list_items_img,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            var imgAnime= holder.layout.findViewById<ImageView>(R.id.imgReader)
            var url = myAnimeURL[position]




            Picasso.get()
                .load(url)
                .into(imgAnime)



    }


    override fun getItemCount(): Int {
        return myAnimeURL.size
    }


}