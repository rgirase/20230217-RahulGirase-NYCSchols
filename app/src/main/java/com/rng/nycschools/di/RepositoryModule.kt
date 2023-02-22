package com.rng.nycschools.di

import com.rng.nycschools.data.repository.NYCSchoolsRepository
import com.rng.nycschools.data.repository.NYCSchoolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dependency Module
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * binds School Repository
     */
    @Binds
    @Singleton
    abstract fun bindNYCSchoolsRepository(nycSchoolsRepositoryImpl: NYCSchoolsRepositoryImpl): NYCSchoolsRepository
}