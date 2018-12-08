package com.giannig.starwarskotlin.data.api

import com.giannig.starwarskotlin.data.dto.StarWarsPlanetList
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiKC {
    @GET("planets")
    fun getPlanetList(): Deferred<StarWarsPlanetList>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id: String): Deferred<StarWarsSinglePlanet>
}