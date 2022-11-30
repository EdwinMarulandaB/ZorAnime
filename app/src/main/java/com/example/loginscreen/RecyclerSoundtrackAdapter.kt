package com.example.loginscreen

import android.annotation.SuppressLint
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

class RecyclerSoundtrackAdapter (contexActivity:AppCompatActivity, val info:ArrayList<String>): RecyclerView.Adapter<RecyclerSoundtrackAdapter.MyViewHolder>() {
    class MyViewHolder(var layout:View):RecyclerView.ViewHolder(layout)


    private var contexto:AppCompatActivity = contexActivity

    var mySongName:ArrayList<String> = info





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.soundtrack_list_items,parent,false)
        return MyViewHolder(layout)
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var textnombre = holder.layout.findViewById<TextView>(R.id.itemNombreCancion)
        textnombre.text=mySongName[position]


    }


    override fun getItemCount(): Int {
        return mySongName.size
    }


}