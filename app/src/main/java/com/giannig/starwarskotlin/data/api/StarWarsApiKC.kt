package com.giannig.starwarskotlin.data.api

import com.giannig.starwarskotlin.data.dto.StarWarsPeople
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import com.giannig.starwarskotlin.data.dto.StarWarsPlanets
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiKC {
    @GET("planets")
    fun getPlanets(): Deferred<StarWarsPlanets>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id:String): Deferred<StarWarsSinglePlanet>

    @GET("people/{id}")
    fun getPeople(@Path("id") id:String): Deferred<List<StarWarsPeople>>
}