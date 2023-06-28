package com.example.androidsample01

import android.content.Intent
import android.util.Log
import android.widget.Toast
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.Response
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.IOException
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val server = MockWebServer()

    @Before
    fun setUp() {
        server.start()
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun addition_isCorrect() {
//        server.enqueue(MockResponse().setResponseCode(200))

        val body = FormBody.Builder()
            .add("username", "user1")
            .add("password", "user1Pass").build()

        val cbLogin = object : Callback {
            override fun onResponse(call: Call, response: Response) {
            }

            override fun onFailure(call: Call, e: IOException) {
            }
        }

//        ApiClient().call(server.url("/login_proc").toString(), body, cbLogin)

//        val req = server.takeRequest()
    }
}