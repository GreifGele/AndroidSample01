package com.example.androidsample01.api.service;

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login_proc")
    fun login (
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @POST("api/posttest")
    fun postTest(): Call<ResponseBody>
}
