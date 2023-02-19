package com.rng.nycschools.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class SchoolResponseEntity(
    val schoolCode: String?,
    val schoolName: String?,
    val location: String?,
    val phoneNumber: String?,
    val schoolEmail: String?,
    val schoolWebsite: String?,
    val city: String?,
    val zip: String?,
    @PrimaryKey val id: Int? = null
)