package com.rng.nycschools.data.repository

import androidx.room.Query
import com.rng.nycschools.data.model.SatScoresResponse
import com.rng.nycschools.data.model.SchoolResponse
import com.rng.nycschools.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NYCSchoolsRepository {

    suspend fun getNYCSchoolList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<SchoolResponse>>>

    suspend fun getSatScores(schoolCode: String): Flow<Resource<SatScoresResponse>>

}