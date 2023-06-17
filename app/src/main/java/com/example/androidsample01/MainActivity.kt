package com.example.androidsample01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import okhttp3.FormBody

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
        //var res = ApiClient().Call(R.string.srv_login.toString(), body)
        var res = ApiClient().Call("login_proc", body)
        Log.i("login", res.toString())
    }
}