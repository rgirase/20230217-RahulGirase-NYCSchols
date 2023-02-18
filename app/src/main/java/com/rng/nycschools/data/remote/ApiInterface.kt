package com.rng.nycschools.data.remote

import com.rng.nycschools.data.model.SatScoresResponse
import com.rng.nycschools.data.model.SchoolResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("/resource/s3k6-pzi2.json")
    suspend fun getNYCSchoolList(): List<SchoolResponse>

    @GET("/resource/f9bf-2cp4.json")
    suspend fun getScores(): List<SatScoresResponse>

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us"
    }
}