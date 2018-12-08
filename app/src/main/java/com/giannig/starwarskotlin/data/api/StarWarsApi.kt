package com.giannig.starwarskotlin.data.api

import com.giannig.starwarskotlin.data.dto.StarWarsPlanetList
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApi {
    @GET("planets")
    fun getPlanetList(): Call<StarWarsPlanetList>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id: String): Call<StarWarsSinglePlanet>
}