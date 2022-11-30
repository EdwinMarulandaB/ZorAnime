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

class RecyclerCapituloAnimeAdapter (contexActivity:AppCompatActivity, val info:Bundle): RecyclerView.Adapter<RecyclerCapituloAnimeAdapter.MyViewHolder>() {
    class MyViewHolder(var layout:View):RecyclerView.ViewHolder(layout)


    private var contexto:AppCompatActivity = contexActivity
    var myAnimeTitles:ArrayList<String> = info.getStringArrayList("title") as ArrayList<String>
    var myAnimeDes:ArrayList<String> = info.getStringArrayList("des") as ArrayList<String>
    var myAnimeURL:ArrayList<String> = info.getStringArrayList("url") as ArrayList<String>
    var img:String= info.getString("imgUrl") as String




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.anime_list_items_list,parent,false)
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            var textTitleAnime = holder.layout.findViewById<TextView>(R.id.tvTituloCaps)
            var textDesAnime = holder.layout.findViewById<TextView>(R.id.tvDescripCaps)
            var imgAnime= holder.layout.findViewById<ImageView>(R.id.imgItemCaps)
            var url = img
            textTitleAnime.text = myAnimeTitles[position]
            textDesAnime.text = myAnimeDes[position]



            Picasso.get()
                .load(url)
                .into(imgAnime)

        info.putString("title",myAnimeTitles[position])
        info.putString("des",myAnimeDes[position])
        info.putString("url",myAnimeURL[position])



        holder.layout.setOnClickListener {

            val cambioActivity = Intent(contexto, VideoPlayer::class.java)
            cambioActivity.putExtra("info",info)
            contexto.startActivity(cambioActivity);

        }

    }


    override fun getItemCount(): Int {
        return myAnimeTitles.size
    }


}