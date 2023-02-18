package com.rng.nycschools.data.repository

import com.rng.nycschools.data.model.SatScoresResponse
import com.rng.nycschools.data.model.SchoolResponse
import com.rng.nycschools.data.remote.ApiInterface
import com.rng.nycschools.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NYCSchoolsRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) :
    NYCSchoolsRepository {
    override suspend fun getNYCSchoolList(): Flow<Resource<List<SchoolResponse>>> {
        return flow {
            emit(Resource.Loading(true))
            val schoolList = try {
                apiInterface.getNYCSchoolList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("", null))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("HTTP API Error ${e.code()} -> ${e.message()}", null))
                null
            }
            schoolList?.let {
                emit(Resource.Success(it))
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getSatScores(schoolCode: String): Flow<Resource<SatScoresResponse>> {
        return flow {
            emit(Resource.Loading(true))
            val satScoreList = try {
                apiInterface.getScores()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("", null))
                emit(Resource.Loading(false))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("HTTP API Error ${e.code()} -> ${e.message()}", null))
                emit(Resource.Loading(false))
                null
            }
            satScoreList?.let { scoreList ->
                emit(Resource.Success(scoreList.single { item -> item.schoolCode == schoolCode }))
            }
        }
    }

}