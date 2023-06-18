package com.example.androidsample01

import android.content.res.Resources
import android.provider.Settings.System.getString
import android.webkit.CookieManager
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody


class ApiClient {
    public fun call(res : Resources, srvcId: Int, body: RequestBody, cb : Callback) {
        val url = res.getString(R.string.srv_base_url) + res.getString(srvcId)
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
