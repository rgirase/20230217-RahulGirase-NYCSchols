package com.rng.nycschools.data.local.mapper

import com.rng.nycschools.data.local.SchoolResponseEntity
import com.rng.nycschools.data.model.SchoolResponse

fun SchoolResponseEntity.toSchoolList(): SchoolResponse {
    return SchoolResponse(
        schoolCode = schoolCode,
        schoolName = schoolName,
        location = location,
        phoneNumber = phoneNumber,
        schoolEmail = schoolEmail,
        schoolWebsite = schoolWebsite,
        city = city,
        zip = zip
    )
}

fun SchoolResponse.toSchoolListingEntity(): SchoolResponseEntity {
    return SchoolResponseEntity(
        schoolCode, schoolName, location, phoneNumber, schoolEmail, schoolWebsite, city, zip
    )
}