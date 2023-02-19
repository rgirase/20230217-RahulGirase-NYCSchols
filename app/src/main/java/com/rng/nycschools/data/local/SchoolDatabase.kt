package com.rng.nycschools.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SchoolResponseEntity::class],
    version = 2
)
abstract class SchoolDatabase : RoomDatabase() {
    abstract val dao: SchoolDao
}