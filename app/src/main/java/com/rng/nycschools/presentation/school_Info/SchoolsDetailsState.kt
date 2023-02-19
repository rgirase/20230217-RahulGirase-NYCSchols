package com.rng.nycschools.presentation.school_Info

import com.rng.nycschools.data.model.SatScoresResponse


data class SchoolsDetailsState(
    val schoolDetails: SatScoresResponse? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = ""
)
