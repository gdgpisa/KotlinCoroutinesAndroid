package com.giannig.starwarskotlin.data.api

import com.giannig.starwarskotlin.data.dto.StarWarsPeople
import com.giannig.starwarskotlin.data.dto.StarWarsSinglePlanet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiRx {
    @GET("planets/")
    fun getPlanets(): Observable<List<StarWarsSinglePlanet>>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id:String): Observable<StarWarsSinglePlanet>

    @GET("people/{id}")
    fun getPeople(@Path("id") id:String): Observable<List<StarWarsPeople>>
}