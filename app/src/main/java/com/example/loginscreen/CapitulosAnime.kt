package com.example.loginscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CapitulosAnime : Fragment() {

    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<RecyclerCapituloAnimeAdapter.MyViewHolder>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_capitulos_anime, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var myAnimeTitles:ArrayList<String> = ArrayList()

        var myAnimeURL:ArrayList<String> = ArrayList()
        var myAnimeDsc:ArrayList<String> = ArrayList()
        var img:String


        myAnimeTitles= arguments?.getStringArrayList("nameVide") as ArrayList<String>
        myAnimeURL= arguments?.getStringArrayList("Link") as ArrayList<String>
        myAnimeDsc= arguments?.getStringArrayList("Description") as ArrayList<String>
        img= arguments?.getString("img") as String






        var info: Bundle = Bundle()

        info.putStringArrayList("title",myAnimeTitles)
        info.putStringArrayList("des",myAnimeDsc)
        info.putStringArrayList("url",myAnimeURL)
        info.putString("imgUrl",img)







        listRecyclerView= requireView().findViewById(R.id.recyclerCapitulosAnime)
        myAdapter=RecyclerCapituloAnimeAdapter(activity as AppCompatActivity,info)
        listRecyclerView.setHasFixedSize(true)
        listRecyclerView.adapter = myAdapter



    }

}