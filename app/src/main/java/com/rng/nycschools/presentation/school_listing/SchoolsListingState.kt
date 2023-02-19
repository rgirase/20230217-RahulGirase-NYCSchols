package com.rng.nycschools.presentation.school_listing

import com.rng.nycschools.data.model.SchoolResponse


data class SchoolsListingState(
    val schools: List<SchoolResponse> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val errorMessage: String? = "",
    val searchQuery: String = ""
)
