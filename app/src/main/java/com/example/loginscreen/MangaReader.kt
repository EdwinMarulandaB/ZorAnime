package com.example.loginscreen

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toolbar
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MangaReader: AppCompatActivity() {

    lateinit var info:Bundle


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_manga_reader)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.colorPrimary)))
        val bundle = intent.extras
        info = bundle?.getBundle("datos")!!
        setTitle(info.getString("title"))



        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_contentManga,fragmet_magaReadear::class.java,info,"todo").commit()

        }
    }



}