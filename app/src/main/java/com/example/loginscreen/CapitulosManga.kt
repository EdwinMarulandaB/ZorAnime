package com.example.loginscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class CapitulosManga : Fragment() {

    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<RecyclerCapituloMangaAdapter.MyViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_capitulos_manga, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var myAnimeTitles:ArrayList<String> = ArrayList()

        var myAnimeURL:ArrayList<String> = ArrayList()
        var nombre:String


        myAnimeTitles= arguments?.getStringArrayList("nCap") as ArrayList<String>
        myAnimeURL= arguments?.getStringArrayList("icon") as ArrayList<String>
        nombre= arguments?.getString("nombre") as String



        var info: Bundle = Bundle()

        info.putStringArrayList("title",myAnimeTitles)
        info.putStringArrayList("url",myAnimeURL)
        info.putString("name",nombre)







        listRecyclerView= requireView().findViewById(R.id.recyclerCapitulosManga)
        myAdapter=RecyclerCapituloMangaAdapter(activity as AppCompatActivity,info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter


    }
}