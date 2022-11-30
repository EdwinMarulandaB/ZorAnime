package com.example.loginscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class fragmet_magaReadear : Fragment() {
    private  lateinit var listRecyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<RecyclerImgMangaAdapter.MyViewHolder>

    var jsonArray: JSONArray = JSONArray()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_img_manga, container, false)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var myCap:String

        var myAnimeURL:ArrayList<String> = ArrayList()
        var nombre:String


        myCap= arguments?.getString("nCap") as String

        nombre= arguments?.getString("title") as String






        val url: String = "http://44.201.89.56/mangacap.php"



        var numerocap :ArrayList<String> = ArrayList()
        var urlIcon:ArrayList<String> = ArrayList()

        val sr: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                jsonArray = JSONArray(response)


                var dia = 0

                while (dia < jsonArray.length()){

                    val jsonObject = JSONObject(jsonArray.getString(dia))


                    myAnimeURL.add(jsonObject.get("Link") as String)

                    dia++
                }

                var info: Bundle = Bundle()

                info.putStringArrayList("url",myAnimeURL)


                listRecyclerView= requireView().findViewById(R.id.recyclerMangaReader)
                myAdapter=RecyclerImgMangaAdapter(activity as AppCompatActivity,info)
                listRecyclerView.setHasFixedSize(true)
                listRecyclerView.adapter = myAdapter
            },
            Response.ErrorListener { volleyError ->

            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params.put("name", nombre)
                params.put("cap", myCap)
                return params
            }
        }
        val queue = Volley.newRequestQueue(activity)
        queue.add(sr)



    }

}