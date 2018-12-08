package com.giannig.starwarskotlin.data

import com.giannig.starwarskotlin.data.api.Api
import com.giannig.starwarskotlin.data.api.StarWarsApi
import com.giannig.starwarskotlin.data.dto.StarWarsPlanetList
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object StarWarsDataProvider {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(StarWarsApi::class.java)

    suspend fun providePlanets(): StarWarsPlanetList {
        return retrofit.getPlanetList().await()
    }

    suspend fun provideSinglePlanet(planetId: String): StarWarsSinglePlanet {
        return retrofit.getPlanet(planetId).await()
    }

    suspend fun getPlanets(counter: Int): List<StarWarsSinglePlanet> {
        val planetList = mutableListOf<StarWarsSinglePlanet>()

        for (id in 1..counter) {
            planetList += provideSinglePlanet(id.toString())
        }

        return planetList
    }
}