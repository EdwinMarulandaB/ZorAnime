package com.example.loginscreen

import android.content.Context
import android.content.pm.ActivityInfo
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {

    lateinit var txtUser: EditText
    lateinit var txtPassword: EditText
    lateinit var btnRegister: Button
    lateinit var checkTerms: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val conexion = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val status = conexion.activeNetworkInfo

        txtUser = findViewById(R.id.user)
        txtPassword = findViewById(R.id.password)
        btnRegister = findViewById(R.id.create)
        checkTerms = findViewById(R.id.terms)


        btnRegister.setOnClickListener() {

            if (status != null && status.isConnected) {
                if (checkTerms.isChecked){
                    addUser()
                }else{
                    Toast.makeText(this, getString(R.string.noTerms), Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, getString(R.string.internet), Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun addUser(){
        val user = txtUser.text.toString()
        val pass = txtPassword.text.toString()
        var msg: String = "no"
        val url: String = "http://44.201.89.56:80/register.php"


        val sr: StringRequest = object : StringRequest(
            Request.Method.POST, url,
            Response.Listener<String> { response ->
                val jsonArray = JSONObject(response)
                if (jsonArray.get("error").toString() == "0"){
                    msg = getString(R.string.userCreate)
                }else if (jsonArray.get("error").toString() == "1"){
                    msg = getString(R.string.fields)
                }else if (jsonArray.get("error").toString() == "2"){
                    msg = getString(R.string.exist)
                }
                Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG).show()
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

}





