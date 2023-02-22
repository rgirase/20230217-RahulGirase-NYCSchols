package com.rng.nycschools.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * School Dao Define database actions here
 */
@Dao
interface SchoolDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchoolListing(
        schoolResponseEntity: List<SchoolResponseEntity>
    )

    @Query("DELETE FROM schoolResponseEntity")
    suspend fun clearSchoolListing()

    @Query("""SELECT * FROM schoolResponseEntity WHERE LOWER(city) LIKE '%' || LOWER(:query) || '%' OR LOWER(zip) LIKE '%' || LOWER(:query) ORDER BY schoolName ASC""")
    suspend fun searchSchoolFromList(query: String): List<SchoolResponseEntity>

    @Query("""SELECT * FROM schoolResponseEntity WHERE LOWER(schoolCode) == LOWER(:schoolCode)""")
    suspend fun searchSchoolBySchoolCode(schoolCode: String): SchoolResponseEntity?

}