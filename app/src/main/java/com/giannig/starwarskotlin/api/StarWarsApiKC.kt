package com.giannig.starwarskotlin.api

import com.giannig.starwarskotlin.model.dto.StarWarsPeople
import com.giannig.starwarskotlin.model.dto.StarWarsPlanet
import com.giannig.starwarskotlin.model.dto.StarWarsPlanets
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiKC {
    @GET("planets")
    fun getPlanets(): Deferred<StarWarsPlanets>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id:String): Deferred<StarWarsPlanet>

    @GET("people/{id}")
    fun getPeople(@Path("id") id:String): Deferred<List<StarWarsPeople>>
}