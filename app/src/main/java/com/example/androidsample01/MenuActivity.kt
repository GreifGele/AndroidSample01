package com.example.androidsample01

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Response
import okio.IOException

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
    }

    fun onClickMenu1(view: View) {
        val cbTest = object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string().orEmpty()
                //Toast.makeText(applicationContext, "posttest Code = " + response.code.toString(), Toast.LENGTH_SHORT)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                //Toast.makeText(applicationContext, "posttest connection error", Toast.LENGTH_SHORT)
            }
        }

        ApiClient().call(resources, R.string.srvc_posttest, FormBody.Builder().build(), cbTest)
    }

    fun onClickMenu2(view: View) {
    }
}