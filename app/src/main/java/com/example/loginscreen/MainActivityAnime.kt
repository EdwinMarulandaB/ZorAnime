package com.example.loginscreen

import android.content.res.Configuration
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray
import org.json.JSONObject

class MainActivityAnime : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle:ActionBarDrawerToggle
    var jsonArray: JSONArray = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_anime)
        setSupportActionBar(findViewById(R.id.toolbar))


        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)

        supportActionBar?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.primary)))

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.content,DirectoryFragment::class.java,null,"todo").commit()

            setTitle("Directorios")
        }



        toggle= setUpDrawerToggle()
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navigationView.setNavigationItemSelectedListener(this)



    }



    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    private fun setUpDrawerToggle(): ActionBarDrawerToggle {
        return ActionBarDrawerToggle(this,
            findViewById(R.id.drawer_layout)
            ,findViewById(R.id.toolbar)
            ,R.string.drawer_open
            ,R.string.drawer_close)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Este es para inflar los menus de afuera
        return super.onCreateOptionsMenu(menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        selectItemNav(item)
        return true
    }

    private fun selectItemNav(item: MenuItem) {
        var fm:FragmentManager= supportFragmentManager
        var ft:FragmentTransaction = fm.beginTransaction()

        when (item.itemId) {

            /*
            R.id.nav_manga->{



                val url: String = "http://44.201.89.56/allmangas.php"



                var numerocap :ArrayList<String> = ArrayList()
                var urlIcon:ArrayList<String> = ArrayList()
                var name:ArrayList<String> = ArrayList()
                var info: Bundle = Bundle()

                val sr: StringRequest = object : StringRequest(
                    Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        jsonArray = JSONArray(response)


                        var dia = 0

                        while (dia < jsonArray.length()){

                            val jsonObject = JSONObject(jsonArray.getString(dia))



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

                }
                val queue = Volley.newRequestQueue(this)
                queue.add(sr)






                ft.replace(R.id.content, CapitulosManga::class.java,null,"").commit()
            }


             */
            R.id.nav_directory ->{
                ft.replace(R.id.content, DirectoryFragment::class.java,null,"").commit()
            }
            /*
            R.id.nav_home ->{
                ft.replace(R.id.content, HomeEjemplo::class.java,null,"").commit()
            }

             */

            /*
            R.id.nav_capitulos ->{



                val url: String = "http://44.201.89.56/caplist.php"



                var UrlVideo :ArrayList<String> = ArrayList()
                var urlName:ArrayList<String> = ArrayList()
                var descrip:ArrayList<String> = ArrayList()
                var urlImg:String
                var info: Bundle = Bundle()

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

                }
                val queue = Volley.newRequestQueue(this)
                queue.add(sr)




                ft.replace(R.id.content, CapitulosAnime::class.java,null,"").commit()
            }
            /*



             */
            R.id.nav_soundtrack ->{
                ft.replace(R.id.content, Soundtracks::class.java,null,"").commit()
            }

             */
            else -> {

            }

        }
        setTitle(item.title)
        drawerLayout.closeDrawers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



}