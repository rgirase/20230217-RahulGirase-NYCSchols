package com.rng.nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SatScoresResponse(
    @SerializedName("dbn") val schoolCode: String = "",
    @SerializedName("school_name") val schoolName: String = "",
    @SerializedName("num_of_sat_test_takers") val testTakers: String = "",
    @SerializedName("sat_critical_reading_avg_score") val readingAvgScore: String = "Not Available",
    @SerializedName("sat_math_avg_score") val mathAvgScore: String = "Not Available",
    @SerializedName("sat_writing_avg_score") val writingAvgScore: String = "Not Available"
)