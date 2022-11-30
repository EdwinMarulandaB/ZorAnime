package com.example.loginscreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerPortadaAdapter (contexActivity:AppCompatActivity, val info:Bundle): RecyclerView.Adapter<RecyclerPortadaAdapter.MyViewHolder>() {
    class MyViewHolder(var layout:View):RecyclerView.ViewHolder(layout)


    private var contexto:AppCompatActivity = contexActivity
    var myAnimeTitles:ArrayList<String> = info.getStringArrayList("title") as ArrayList<String>
    var myAnimeDes:ArrayList<String> = info.getStringArrayList("des") as ArrayList<String>
    var myAnimeURL:ArrayList<String> = info.getStringArrayList("url") as ArrayList<String>
    var numCapitulos:ArrayList<String> = info.getStringArrayList("nCap") as ArrayList<String>
    var nameEditorial:ArrayList<String> = info.getStringArrayList("Producer") as ArrayList<String>




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.anime_list_items_cuadro,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var textTitleAnime = holder.layout.findViewById<TextView>(R.id.tvTitulo)

            var imgAnime= holder.layout.findViewById<ImageView>(R.id.imgItem)
            var url = myAnimeURL[position]
            textTitleAnime.text = myAnimeTitles[position]


        var info: Bundle = Bundle()

        info.putString("title",myAnimeTitles[position])
        info.putString("des",myAnimeDes[position])
        info.putString("url",myAnimeURL[position])
        info.putString("nCap",numCapitulos[position])
        info.putString("Producer",nameEditorial[position])

            Picasso.get()
                .load(url)
                .into(imgAnime)

        holder.layout.setOnClickListener {

            val cambioActivity = Intent(contexto,AnimeActivity::class.java)
            cambioActivity.putExtra("info",info)
            contexto.startActivity(cambioActivity);

        }

    }


    override fun getItemCount(): Int {
        return myAnimeTitles.size
    }


}