package com.rng.nycschools.presentation.school_listing
/**
 * Events on SchoolListHome screen
 */
sealed class SchoolListingEvents {
    object Refresh : SchoolListingEvents()
    data class OnSearchQueryChange(val query: String) : SchoolListingEvents()
}
