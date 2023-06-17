package com.example.androidsample01

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.IOException

class ApiClient {
    public fun Call(service: String, body: RequestBody) {
        //var url = R.string.srv_base_url.toString() + service
        var url = "http://10.0.2.2:8080/$service"
        var req = Request.Builder()
            .url(url)
            .post(body).build()
        var client = OkHttpClient()
        var call = client.newCall(req)
        return call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                // Responseの読み出し
                val responseBody = response.body?.string().orEmpty()
                // 必要に応じてCallback
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                // 必要に応じてCallback
            }
        })
    }
}
