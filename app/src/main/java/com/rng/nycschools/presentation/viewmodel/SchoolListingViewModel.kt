package com.rng.nycschools.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rng.nycschools.data.repository.NYCSchoolsRepository
import com.rng.nycschools.presentation.school_listing.SchoolListingEvents
import com.rng.nycschools.presentation.school_listing.SchoolsListingState
import com.rng.nycschools.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SchoolListingViewModel @Inject constructor(private val repository: NYCSchoolsRepository) :
    ViewModel() {
    var stateSchoolList by mutableStateOf(SchoolsListingState())
    private var searchJob: Job? = null

    init {
        getSchoolListing()
    }

    private fun getSchoolListing(
        query: String = stateSchoolList.searchQuery.lowercase(
            Locale.getDefault()
        ), fetchRemote: Boolean = false
    ) {
        viewModelScope.launch {

            repository.getNYCSchoolList(fetchRemote, query).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        stateSchoolList = stateSchoolList.copy(
                            isLoading = false, errorMessage = result.message
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { list ->
                            stateSchoolList = stateSchoolList.copy(schools = list)
                        }
                    }
                    is Resource.Loading -> {
                        stateSchoolList = stateSchoolList.copy(isLoading = result.isLoading)
                    }
                }

            }
        }

    }

    fun onEvent(event: SchoolListingEvents) {
        when (event) {
            is SchoolListingEvents.OnSearchQueryChange -> {
                stateSchoolList = stateSchoolList.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getSchoolListing()
                }
            }
            SchoolListingEvents.Refresh -> getSchoolListing(fetchRemote = true)
        }
    }


}