package com.example.loginscreen

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton

class Soundtracks : Fragment() {
    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<RecyclerSoundtrackAdapter.MyViewHolder>

    val mp by lazy {
        val m = MediaPlayer()
        val canciones= arguments?.getStringArrayList("urlM")as ArrayList<String>
        m.setDataSource(canciones[0])
        m.prepare()
        m
    }




   object ci{
       val prev =0
       val stop =1
       val play =2
       val next =3

   }




    val nombreCancion by lazy {
        requireView().findViewById<TextView>(R.id.nombreCancion)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_soundtrack, container, false)
    }

    var cancionActualIndex=0
    var nombres=ArrayList<String>()
    var canciones=ArrayList<String>()
    var numero=0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            canciones= arguments?.getStringArrayList("urlM")as ArrayList<String>
            nombres = arguments?.getStringArrayList("name") as ArrayList<String>
            numero = arguments?.getInt("numero") as Int



        requireView().findViewById<Button>(R.id.play).setOnClickListener(this::playClicked)
        requireView().findViewById<Button>(R.id.stop).setOnClickListener(this::stopClicked)
        requireView().findViewById<Button>(R.id.prev).setOnClickListener(this::prevClick)
        requireView().findViewById<Button>(R.id.next).setOnClickListener(this::nextClick)


            nombreCancion.text= nombres[cancionActualIndex]







    /*
        var myAnimeTitles:ArrayList<String> = ArrayList()

        myAnimeTitles.add("Gerra de titanes")
        myAnimeTitles.add("Dragon ball")
        myAnimeTitles.add("Los mecheros del amor")
        myAnimeTitles.add("Haikyuu")


        var myAnimeDsc:ArrayList<String> = ArrayList()

        myAnimeDsc.add("Salen unos titanes ree pelotudos de las paredes")
        myAnimeDsc.add("Son unos pelotudos que solo viven para darse en la cara bro")
        myAnimeDsc.add("Son gays")
        myAnimeDsc.add("El mejor anime de la historia")



        var myAnimeURL:ArrayList<String> = ArrayList()

        myAnimeURL.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROdYj9HINKn1Q_yEkSmtMXomJyuNkEeqfKsg&usqp=CAU")

        myAnimeURL.add("https://www.crunchyroll.com/imgsrv/display/thumbnail/480x720/catalog/crunchyroll/35e4ac6339f5fdcc164160a5755790cd.jpeg")
        myAnimeURL.add("https://i.pinimg.com/736x/17/c0/62/17c062a1ef0aa891b31d20e9e482c5a9.jpg")
        myAnimeURL.add("https://zoranime.s3.amazonaws.com/animes/Haikyuu/icon/Haikyuu.jpg")



        var myAnimePrueba:ArrayList<String> = ArrayList()

        myAnimePrueba.add("nasheeeeeee dfasfasfasf")
        myAnimePrueba.add("nasheeeeeee 32523523")
        myAnimePrueba.add("que locota 32523523")
        myAnimePrueba.add("Care pastel 32523523")

        var info: Bundle = Bundle()

        info.putStringArrayList("title",myAnimeTitles)
        info.putStringArrayList("des",myAnimeDsc)
        info.putStringArrayList("url",myAnimeURL)
        info.putStringArrayList("prueba",myAnimePrueba)

*/


    }


    override fun onDestroyView() {
        super.onDestroyView()
        mp.seekTo(0)
        mp.pause()
    }

    fun move(){
        if (cancionActualIndex==-1){
            cancionActualIndex=numero-1
        }else if (cancionActualIndex>=numero){
            cancionActualIndex=0
        }
    }

    fun playClicked(v:View){
        if (!mp.isPlaying){
            mp.start()
            requireView().findViewById<Button>(R.id.play).setText("Pause")
            nombreCancion.visibility = View.VISIBLE
        }else{
            mp.pause()
            requireView().findViewById<Button>(R.id.play).setText("Play")
        }


    }

    fun stopClicked(v:View){
        if (mp.isPlaying){
            mp.pause()
            requireView().findViewById<Button>(R.id.play).setText("Play")
            nombreCancion.visibility = View.INVISIBLE
        }
        mp.seekTo(0)
    }

    fun nextClick(v:View){
        cancionActualIndex++
        move()
        refreshSong()
    }

    fun prevClick(v:View){
        cancionActualIndex--
        move()
        refreshSong()
    }

    fun refreshSong(){
        mp.reset()
        val url = canciones[cancionActualIndex]
        mp.setDataSource(url)
        mp.prepare()
        playClicked(requireView().findViewById<Button>(R.id.play))
        nombreCancion.text = nombres[cancionActualIndex]
    }





}