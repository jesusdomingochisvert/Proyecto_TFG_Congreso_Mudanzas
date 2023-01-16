package com.example.congresotfg.common.utils

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface LocationApiService {

    @GET(value = "/v2/directions/driving-car")
    suspend fun getRoute(

        @Query(value = "api_key") key: String,
        @Query(value = "start", encoded = true) start: String,
        @Query(value = "end", encoded = true) end: String

    ): Response<RouteResponse>

}