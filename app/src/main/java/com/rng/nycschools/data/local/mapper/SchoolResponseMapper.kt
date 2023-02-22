package com.rng.nycschools.data.local.mapper

import com.rng.nycschools.data.local.SchoolResponseEntity
import com.rng.nycschools.data.model.SchoolResponse


// List extension to convert  SchoolResponseEntity to SchoolResponse object
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

// List extension to convert SchoolResponse  to SchoolResponseEntity object
fun SchoolResponse.toSchoolListingEntity(): SchoolResponseEntity {
    return SchoolResponseEntity(
        schoolCode, schoolName, location, phoneNumber, schoolEmail, schoolWebsite, city, zip
    )
}

