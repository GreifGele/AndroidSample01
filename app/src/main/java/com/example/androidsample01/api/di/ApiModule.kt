package com.example.androidsample01.api.di

import android.webkit.CookieManager
import com.example.androidsample01.api.interceptor.ApiInterceptor
import com.example.androidsample01.api.service.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideOkHttp(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()

        logging.level = if (true) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        builder.addInterceptor(logging)
        builder.addInterceptor(ApiInterceptor())
        builder.cookieJar(getCookieJar())

        return builder.build()
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

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://192.168.0.127:8080/")
//            .addConverterFactory(
//                Json {
//                    ignoreUnknownKeys = true
//                }.asConverterFactory(
//                    "application/json".toMediaType()
//                ),
//            )
            .build()
}
