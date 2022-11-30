package com.example.loginscreen



import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.palette.graphics.Palette
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso


class AnimeActivity: AppCompatActivity() {

    lateinit var info:Bundle
    var jsonArray:JSONArray=JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_vista_anime)
        setSupportActionBar(findViewById(R.id.toolbarAnime))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val bundle = intent.extras
        info = bundle?.getBundle("info")!!



        var img:ImageView = findViewById<ImageView>(R.id.imgAnime)
        var title:TextView = findViewById<TextView>(R.id.acTitle)
        var desc:TextView = findViewById<TextView>(R.id.descripcion)
        var ncaps:TextView = findViewById<TextView>(R.id.episodios)
        var editorial:TextView = findViewById<TextView>(R.id.editorial)
        var fdf:TextView = findViewById<TextView>(R.id.text_ratim)
        var ratingBar:RatingBar = findViewById<RatingBar>(R.id.rb_ratinBar)

        var myAnimeTitles: String? = info.getString("title")
        var myAnimeDes:String? = info.getString("des")
        var myAnimeURL:String? = info.getString("url")
        var numCapitulos:String? = info.getString("nCap")
        var nameEditorial:String? = info.getString("Producer")


        title.text= myAnimeTitles
        desc.text=myAnimeDes
        ncaps.text=numCapitulos
        editorial.text=nameEditorial

        Picasso.get()
            .load(myAnimeURL)
            .into(img)

        setTitle(myAnimeTitles)

        ratingBar.rating=2.5f
        ratingBar.stepSize=.5f

        ratingBar.setOnRatingBarChangeListener{ratingBar,rating,fromUser ->
            fdf.text = "$rating/5"
        }

        var bitmap: Bitmap = BitmapFactory.decodeResource(resources,R.drawable.anime)
        var collapsingToolbarLayout:CollapsingToolbarLayout = findViewById(R.id.collapsig)

        Palette.from(bitmap).generate{ palette ->
            if (palette !=null){
                collapsingToolbarLayout.setContentScrimColor(resources.getColor(R.color.colorPrimary))
            }
        }











    }


    fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonCapitulos->{



                val url: String = "http://44.201.89.56/caplist.php"



                var UrlVideo :ArrayList<String> = ArrayList()
                var urlName:ArrayList<String> = ArrayList()
                var descrip:ArrayList<String> = ArrayList()
                var urlImg:String

                val sr: StringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        jsonArray = JSONArray(response)


                        var dia = 0

                        while (dia < jsonArray.length()){

                            val jsonObject = JSONObject(jsonArray.getString(dia))


                            urlName.add(jsonObject.get("Name") as String)
                            descrip.add(jsonObject.get("Description") as String)
                            UrlVideo.add(jsonObject.get("Link") as String)

                            dia++
                        }

                        if (descrip.size == 0){
                            Toast.makeText(this, "No tiene capitulos por el momento", Toast.LENGTH_SHORT).show()
                        }else{

                            urlImg=info.getString("url") as String

                            info.putStringArrayList("nameVide",urlName)
                            info.putStringArrayList("Description",descrip)
                            info.putStringArrayList("Link",UrlVideo)
                            info.putString("img",urlImg)




                            supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                                .replace(R.id.fragmenContenido,CapitulosAnime::class.java,info,"")
                                .addToBackStack("")
                                .commit()

                        }


                    },
                    Response.ErrorListener { volleyError ->

                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()

                        params.put("name", info.getString("title") as String)
                        return params
                    }
                }
                val queue = Volley.newRequestQueue(this)
                queue.add(sr)







            }

            R.id.buttonManga ->{




                val url: String = "http://44.201.89.56/mangaList.php"



                var numerocap :ArrayList<String> = ArrayList()
                var urlIcon:ArrayList<String> = ArrayList()

                val sr: StringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        jsonArray = JSONArray(response)


                        var dia = 0

                        while (dia < jsonArray.length()){

                            val jsonObject = JSONObject(jsonArray.getString(dia))


                            urlIcon.add(jsonObject.get("Icon") as String)
                            numerocap.add(jsonObject.get("nCap") as String)


                            dia++
                        }

                        if (urlIcon.size == 0){
                            Toast.makeText(this, "No tiene capitulos por el momento", Toast.LENGTH_SHORT).show()
                        }else{

                            info.putStringArrayList("icon",urlIcon)
                            info.putStringArrayList("nCap",numerocap)
                            info.putString("nombre", info.getString("title") as String)


                            supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                                .replace(R.id.fragmenContenido,CapitulosManga::class.java,info,"")
                                .addToBackStack("")
                                .commit()

                        }


                    },
                    Response.ErrorListener { volleyError ->

                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()

                        params.put("name", info.getString("title") as String)
                        return params
                    }
                }
                val queue = Volley.newRequestQueue(this)
                queue.add(sr)























            }

            R.id.buttonSoundtrack ->{


                val url: String = "http://44.201.89.56/soundlist.php"



                var nombre :ArrayList<String> = ArrayList()
                var myAnimeURLMusic:ArrayList<String> = ArrayList()

                val sr: StringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        jsonArray = JSONArray(response)


                        var dia = 0

                        while (dia < jsonArray.length()){

                            val jsonObject = JSONObject(jsonArray.getString(dia))


                            myAnimeURLMusic.add(jsonObject.get("Link") as String)
                            nombre.add(jsonObject.get("Name") as String)


                            dia++
                        }

                        if (myAnimeURLMusic.size == 0){
                            Toast.makeText(this, "No tiene openings por el momento", Toast.LENGTH_SHORT).show()
                        }else{
                            info.putInt("numero",myAnimeURLMusic.size)
                            info.putStringArrayList("name",nombre)
                            info.putStringArrayList("urlM",myAnimeURLMusic)


                            supportFragmentManager.beginTransaction().setReorderingAllowed(true)
                                .replace(R.id.fragmenContenido,Soundtracks::class.java,info,"")
                                .addToBackStack("")
                                .commit()
                        }


                    },
                    Response.ErrorListener { volleyError ->

                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()

                        params.put("name", info.getString("title") as String)
                        return params
                    }
                }
                val queue = Volley.newRequestQueue(this)
                queue.add(sr)

            }

            else -> {

            }

        }
    }






}


