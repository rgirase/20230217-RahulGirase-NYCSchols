package com.rng.nycschools.data.repository

import androidx.room.Query
import com.rng.nycschools.data.local.SchoolDatabase
import com.rng.nycschools.data.local.SchoolResponseEntity
import com.rng.nycschools.data.local.mapper.toSchoolList
import com.rng.nycschools.data.local.mapper.toSchoolListingEntity
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

/**
 *  Repository to Handle remote and local data
 */
@Singleton
class NYCSchoolsRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val db: SchoolDatabase
) :
    NYCSchoolsRepository {
    private val dao = db.dao
    override suspend fun getNYCSchoolList(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<SchoolResponse>>> {
        return flow {
            emit(Resource.Loading(true))
            // fetch local data if available
            val localSchoolList = dao.searchSchoolFromList(query)
            emit(Resource.Success(data = localSchoolList.map { it.toSchoolList() }))

            val isDBEmpty = localSchoolList.isEmpty() && query.isBlank()
            val shouldLoadFromCache = !isDBEmpty && !fetchFromRemote

            // if local database has the list then return
            if (shouldLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            // fetch school list from remote api
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
            schoolList?.let { list ->
                // clear previous local list
                dao.clearSchoolListing()
                // Save newly fetched list to db
                dao.insertSchoolListing(list.map { it.toSchoolListingEntity() })
                // Return the latest school list
                emit(
                    Resource.Success(
                        data = dao.searchSchoolFromList("").map { it.toSchoolList() })
                )
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getSatScores(schoolCode: String): Flow<Resource<SatScoresResponse>> {
        return flow {
            emit(Resource.Loading(true))
            // fetch sat score from remote api
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
                //filter the School item according to School Code
                val schoolItem: SatScoresResponse? =
                    scoreList.singleOrNull { item -> item.schoolCode == schoolCode }
                if (schoolItem != null) {
                    emit(Resource.Success(schoolItem))
                } else {
                    emit(Resource.Success(SatScoresResponse()))
                }
                emit(Resource.Loading(false))
            }
        }
    }

    override suspend fun getSchoolInfoByCode(schoolCode: String): Flow<Resource<SchoolResponseEntity>> {
        return flow {
            //fetch school item from local database matching requested SchoolCode
            val schoolItem = dao.searchSchoolBySchoolCode(schoolCode)
            if (schoolItem != null) {
                emit(Resource.Success(data = schoolItem))
            }

        }
    }

}