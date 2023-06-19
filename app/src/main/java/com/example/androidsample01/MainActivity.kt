package com.example.androidsample01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.IntentCompat
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Response
import okio.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickLogin(view: View) {
        Toast.makeText(this.applicationContext,"button clicked", Toast.LENGTH_SHORT).show();
        Log.i("login", "do");

        val body = FormBody.Builder()
            .add("username", "user1")
            .add("password", "user1Pass").build()

        val cbLogin = object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string().orEmpty()

                if (response.code == 200) {
                    val intent = Intent(applicationContext, MenuActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                } else {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                //Toast.makeText(applicationContext, "Connection Error", Toast.LENGTH_SHORT).show()
            }
        }

        ApiClient().call(resources, R.string.srvc_login, body, cbLogin)
    }
}