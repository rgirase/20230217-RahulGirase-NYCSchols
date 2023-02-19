package com.rng.nycschools.presentation.school_listing

sealed class SchoolListingEvents {
    object Refresh : SchoolListingEvents()
    data class OnSearchQueryChange(val query: String) : SchoolListingEvents()
}
