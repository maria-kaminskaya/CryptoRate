package com.example.cryptorate.api

import com.example.cryptorate.data.CryptoList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ServerApi{
//    @GET("cryptocurrency/listings/latest")
//    fun loadData(@Query("limit") limit: Int): Deferred<List<Data>>

    @Headers("X-CMC_PRO_API_KEY: 3069b872-06b0-40f1-bf44-5a3f7f73d796")
    @GET("/v1/cryptocurrency/listings/latest?")
    fun loadData(@Query("limit") page: Int): Call<CryptoList>
}