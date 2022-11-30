package com.example.loginscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var txtUser: EditText
    lateinit var txtPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtUser = findViewById(R.id.user)
        txtPassword = findViewById(R.id.password)
    }

    fun onClickRegister(view: View){
        val registro = Intent(this,RegisterActivity::class.java)
        startActivity(registro)
    }

    fun onClickLogin(view: View){
        val user = txtUser.text.toString()
        val pass = txtPassword.text.toString()
        var msg: String = ""
        val url: String = "http://44.201.89.56:80/login.php"


        val sr: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                val jsonArray = JSONObject(response)
                if (jsonArray.get("error").toString() == "0"){
                    val registro = Intent(this,MainActivityAnime::class.java)
                    startActivity(registro)

                }else if (jsonArray.get("error").toString() == "1"){
                    msg = getString(R.string.fields)
                }else if (jsonArray.get("error").toString() == "2"){
                    msg = getString(R.string.noLogin)
                }


                txtUser.setText("")
                txtPassword.setText("")
            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(applicationContext, volleyError.toString(), Toast.LENGTH_LONG).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("user", user)
                params.put("pass", pass)
                return params
            }
        }

        val queue = Volley.newRequestQueue(this)
        queue.add(sr)

    }

    fun prueba(view: View){

        val url: String = "http://44.201.89.56/animelist.php"


        val sr: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                val jsonArray = JSONArray(response)

                for(i in 0 until jsonArray.length()){
                    val jsonObject = JSONObject(jsonArray.getString(i))
                    var text = jsonObject.get("Description")
                    Toast.makeText(applicationContext,text.toString(),Toast.LENGTH_LONG).show()
                }

            },
            Response.ErrorListener { volleyError ->
                Toast.makeText(applicationContext, volleyError.toString(), Toast.LENGTH_LONG).show()
            }) {
        }

        val queue = Volley.newRequestQueue(this)
        queue.add(sr)

    }
}



