package com.giannig.starwarskotlin.data.api

import com.giannig.starwarskotlin.data.dto.StarWarsPeople
import com.giannig.starwarskotlin.data.dto.StarWarsPlanet
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiRx {
    @GET("planets/")
    fun getPlanets(): Observable<List<StarWarsPlanet>>

    @GET("planets/{id}")
    fun getPlanet(@Path("id") id:String): Observable<StarWarsPlanet>

    @GET("people/{id}")
    fun getPeople(@Path("id") id:String): Observable<List<StarWarsPeople>>
}