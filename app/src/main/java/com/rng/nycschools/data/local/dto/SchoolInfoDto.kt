package com.rng.nycschools.data.local.dto

import com.squareup.moshi.Json

data class SchoolInfoDto(
    @field:Json(name = "SchoolCode") val schoolCode: String,
    @field:Json(name = "SchoolName") val schoolName: String,
    @field:Json(name = "Location") val location: String,
    @field:Json(name = "PhoneNumber") val phoneNumber: String,
    @field:Json(name = "SchoolEmail") val schoolEmail: String,
    @field:Json(name = "SchoolWebsite") val schoolWebsite: String,
    @field:Json(name = "City") val schoolCity: String,
    @field:Json(name = "ZipCode") val schoolZipCode: String
)
