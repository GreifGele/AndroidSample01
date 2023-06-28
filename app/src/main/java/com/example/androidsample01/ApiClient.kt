package com.example.androidsample01

import android.content.res.Resources
import android.webkit.CookieManager
import com.example.androidsample01.api.ApiInterceptor
import com.example.androidsample01.api.ApiService
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import retrofit2.Retrofit


class ApiClient {

    fun getApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.127:8080/")
//            .baseUrl("http://10.0.2.2:8080/")
            .client(getClient())
            .build()
            .create(ApiService::class.java)
    }

    fun getClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cookieJar(getCookieJar())
            .addInterceptor(ApiInterceptor())
            .build()
    }

    public fun call(res : Resources, srvcId: Int, body: RequestBody, cb : Callback) {
        val url = res.getString(R.string.srv_base_url) + res.getString(srvcId)
        call(url, body, cb)
    }

    public fun call(url: String, body: RequestBody, cb : Callback) {
        val req = Request.Builder()
            .url(url)
            .addHeader("User-Agent", "Mobile") // for simulator
            .post(body).build()
        val client = OkHttpClient().newBuilder().cookieJar(getCookieJar()).build()
        return client.newCall(req).enqueue(cb)
    }

    private fun getCookieJar() : CookieJar {
        return object : CookieJar {
            override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
                val cookieManager = CookieManager.getInstance()
                for (cookie in cookies) {
                    cookieManager.setCookie(url.toString(), cookie.toString())
                }
            }

            override fun loadForRequest(url: HttpUrl): List<Cookie> {
                val cookieManager = CookieManager.getInstance()
                val cookies: MutableList<Cookie> = ArrayList()
                val cookieStr = cookieManager.getCookie(url.toString())
                if (cookieStr != null) {
                    cookies.add(Cookie.parse(url, cookieStr)!!)
                }
                return cookies
            }
        }
    }
}
