package com.example.androidsample01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
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

        var body = FormBody.Builder()
            .add("username", "user1")
            .add("password", "user1Pass").build()

        var cbLogin = object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string().orEmpty()

                var cbTest = object : Callback {
                    override fun onResponse(call: Call, response: Response) {
                        val responseBody = response.body?.string().orEmpty()
                    }

                    override fun onFailure(call: Call, e: IOException) {
                        Log.e("Error", e.toString())
                    }
                }

                ApiClient().call("api/posttest", FormBody.Builder().build(), cbTest)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
            }
        }

        ApiClient().call("login_proc", body, cbLogin)
    }
}