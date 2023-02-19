package com.rng.nycschools.presentation.school_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rng.nycschools.data.repository.NYCSchoolsRepository
import com.rng.nycschools.presentation.school_Info.SchoolsDetailsState
import com.rng.nycschools.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolListingViewModel @Inject constructor(private val repository: NYCSchoolsRepository) :
    ViewModel() {
    var stateSchoolList by mutableStateOf(SchoolsListingState())

    init {
        getSchoolListing()
    }

    private fun getSchoolListing() {
        viewModelScope.launch {

            repository.getNYCSchoolList().collect { result ->
                when (result) {
                    is Resource.Error -> {
                        stateSchoolList = stateSchoolList.copy(
                            isLoading = false,
                            errorMessage = result.message
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
            is SchoolListingEvents.OnSearchQueryChange -> TODO()
            SchoolListingEvents.Refresh -> TODO()
        }
    }


}