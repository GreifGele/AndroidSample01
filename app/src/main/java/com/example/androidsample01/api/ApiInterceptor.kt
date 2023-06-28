package com.example.androidsample01.api

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request().newBuilder().header("User-Agent", "Mobile").build()
        return chain.proceed(req)
    }
}