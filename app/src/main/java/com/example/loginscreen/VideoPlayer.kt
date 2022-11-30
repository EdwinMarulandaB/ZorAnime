package com.example.loginscreen

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class VideoPlayer: AppCompatActivity() {
    lateinit var info:Bundle

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        info = bundle?.getBundle("info")!!





        var title:TextView = findViewById<TextView>(R.id.acTitleCapitulo)
        var desc:TextView = findViewById<TextView>(R.id.descripcionVideo)


        var myAnimeTitles: String? = info.getString("title")
        var myAnimeDes:String? = info.getString("des")
        var myAnimeURL:String? = info.getString("url")

        title.text=myAnimeTitles
        desc.text=myAnimeDes


        var video: VideoView = findViewById<VideoView>(R.id.videoView)
        val onlineURL = Uri.parse(myAnimeURL)
        val mediaController = MediaController(this)
        video.setVideoURI(onlineURL)
        mediaController.setAnchorView(video)
        video.setMediaController(mediaController)
        video.start()

    }
}