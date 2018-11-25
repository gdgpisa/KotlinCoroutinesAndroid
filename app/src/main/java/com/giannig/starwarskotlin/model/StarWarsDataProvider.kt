package com.giannig.starwarskotlin.model

import com.giannig.starwarskotlin.api.Api
import com.giannig.starwarskotlin.api.StarWarsApiKC
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StarWarsDataProvider {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(StarWarsApiKC::class.java)

    fun providePlanets() = retrofit.getPlanets()


    fun providePlanet(id: String) = retrofit.getPlanet(id)

    fun providePeople(id: String) = retrofit.getPeople(id)
}