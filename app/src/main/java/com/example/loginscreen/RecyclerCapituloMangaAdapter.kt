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

class RecyclerCapituloMangaAdapter (contexActivity:AppCompatActivity, val info:Bundle): RecyclerView.Adapter<RecyclerCapituloMangaAdapter.MyViewHolder>() {
    class MyViewHolder(var layout:View):RecyclerView.ViewHolder(layout)


    private var contexto:AppCompatActivity = contexActivity
    var myAnimeTitles:ArrayList<String> = info.getStringArrayList("title") as ArrayList<String>

    var myAnimeURL:ArrayList<String> = info.getStringArrayList("url") as ArrayList<String>
    var nombre:String= info.getString("name") as String



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.manga_list_items,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var textTitleAnime = holder.layout.findViewById<TextView>(R.id.tvTituloManga)

            var imgAnime= holder.layout.findViewById<ImageView>(R.id.imgItemManga)
            var url = myAnimeURL[position]
            textTitleAnime.text = "Capitulo:"+myAnimeTitles[position]

        var info: Bundle = Bundle()

        info.putString("title",nombre)

        info.putString("url",myAnimeURL[position])
        info.putString("nCap",myAnimeTitles[position])



            Picasso.get()
                .load(url)
                .into(imgAnime)

        holder.layout.setOnClickListener {

            val cambioActivity = Intent(contexto, MangaReader::class.java)
            cambioActivity.putExtra("datos",info)
            contexto.startActivity(cambioActivity);

        }

    }


    override fun getItemCount(): Int {
        return myAnimeTitles.size
    }


}