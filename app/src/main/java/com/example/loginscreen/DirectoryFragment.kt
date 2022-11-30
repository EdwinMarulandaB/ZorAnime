package com.example.loginscreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class DirectoryFragment : Fragment() {

    private  lateinit var listRecyclerView:RecyclerView
    private lateinit var myAdapter:RecyclerView.Adapter<RecyclerPortadaAdapter.MyViewHolder>


    @SuppressLint("MissingInflatedId", "CutPasteId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmento = inflater.inflate(R.layout.fragment_directory, container, false)
        return fragmento


    }

    var jsonArray:JSONArray=JSONArray()
    var myAnimeTitles:ArrayList<String> = ArrayList()
    var myAnimeDsc:ArrayList<String> = ArrayList()
    var myAnimeURL:ArrayList<String> = ArrayList()
    var numCapitulos:ArrayList<String> = ArrayList()
    var nameEditorial:ArrayList<String> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val url: String = "http://44.201.89.56/animelist.php"




        val sr: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                jsonArray = JSONArray(response)

                var dia = 0

                while (dia < jsonArray.length()){

                    val jsonObject = JSONObject(jsonArray.getString(dia))

                    myAnimeTitles.add(jsonObject.get("Name") as String)
                    myAnimeDsc.add(jsonObject.get("Description") as String)
                    myAnimeURL.add(jsonObject.get("Icon") as String)
                    numCapitulos.add(jsonObject.get("nCaps") as String)
                    nameEditorial.add(jsonObject.get("Producer") as String)

                    dia++
                }

                var info: Bundle = Bundle()

                info.putStringArrayList("title",myAnimeTitles)
                info.putStringArrayList("des",myAnimeDsc)
                info.putStringArrayList("url",myAnimeURL)
                info.putStringArrayList("nCap",numCapitulos)
                info.putStringArrayList("Producer",nameEditorial)




                var gridLayouMAnager:  GridLayoutManager = GridLayoutManager(activity,3,GridLayoutManager.VERTICAL,false)


                listRecyclerView= requireView().findViewById(R.id.recyclerId)
                myAdapter=RecyclerPortadaAdapter(activity as AppCompatActivity,info)
                listRecyclerView.setHasFixedSize(true)
                listRecyclerView.layoutManager = gridLayouMAnager
                listRecyclerView.adapter = myAdapter

                                      },
            Response.ErrorListener { volleyError ->

            }) {
        }
        val queue = Volley.newRequestQueue(activity)
        queue.add(sr)




    }


}