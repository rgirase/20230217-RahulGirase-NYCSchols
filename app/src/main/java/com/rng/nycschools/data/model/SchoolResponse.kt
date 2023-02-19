package com.rng.nycschools.data.model

import com.google.gson.annotations.SerializedName

data class SchoolResponse(
    @SerializedName("dbn") val schoolCode: String?,
    @SerializedName("school_name") val schoolName: String?,
    @SerializedName("location") val location: String?,
    @SerializedName("phone_number") val phoneNumber: String?,
    @SerializedName("school_email") val schoolEmail: String?,
    @SerializedName("website") val schoolWebsite: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("zip") val zip: String?
)
