package com.giannig.starwarskotlin.data

import com.giannig.starwarskotlin.data.api.Api
import com.giannig.starwarskotlin.data.api.StarWarsApiKC
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object StarWarsDataProvider {

    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(Api.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build().create(StarWarsApiKC::class.java)

    suspend fun providePlanets(): State {
        return try {
            val planets = retrofit.getPlanets().await()
            State.PlanetList(planets.results)
        }catch (e: IOException){
            State.Error
        }
    }

    suspend fun providePlanet(planetId: String): State {
        return try {
            val planet = retrofit.getPlanet(planetId).await()
            State.Planet(planet)
        }catch (e: IOException){
            State.Error
        }
    }
}

sealed class State {
    data class Planet(val planet: StarWarsSinglePlanet):State()
    data class PlanetList(val result: List<StarWarsSinglePlanet>?): State()
    object Error : State()
}