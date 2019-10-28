package coder.test.retroking.services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    // URL
    // OKHttpClient.Builder Object
    // Retrofit.Builder Object
    // Retrofit Object
    // createService Function


    private const val  BASE_URL = "https://jsonplaceholder.typicode.com"

    private val OKHttp : OkHttpClient.Builder = OkHttpClient.Builder()

    private val builder : Retrofit.Builder  = Retrofit.Builder()
        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
        .client(OKHttp.build())

    private val retrofit : Retrofit = builder.build()

    fun <T> createService(st:Class<T>):T{
        return retrofit.create(st)
    }
}