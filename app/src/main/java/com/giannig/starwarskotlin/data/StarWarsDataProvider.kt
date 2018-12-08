package com.giannig.starwarskotlin.data

import com.giannig.starwarskotlin.data.api.Api
import com.giannig.starwarskotlin.data.api.StarWarsApi
import com.giannig.starwarskotlin.data.dto.StarWarsPlanetList
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StarWarsDataProvider {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(StarWarsApi::class.java)

    fun providePlanets(): Call<StarWarsPlanetList> {
        return retrofit.getPlanetList()
    }

    fun provideSinglePlanet(planetId: String): Call<StarWarsSinglePlanet> {
        return retrofit.getPlanet(planetId)
    }
}