package com.rng.nycschools.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rng.nycschools.data.model.SatScoresResponse
import com.rng.nycschools.data.repository.NYCSchoolsRepository
import com.rng.nycschools.presentation.school_Info.SchoolsDetailsState
import com.rng.nycschools.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: NYCSchoolsRepository
) :
    ViewModel() {
    var stateSchoolSatScore by mutableStateOf(SchoolsDetailsState())

    init {
        viewModelScope.launch {
            val schoolCode = savedStateHandle.get<String>("schoolID") ?: return@launch
            stateSchoolSatScore = stateSchoolSatScore.copy(isLoading = true)
            // fetch sat score from remote api and filter
            repository.getSatScores(schoolCode).collect { result ->
                when (result) {
                    is Resource.Error -> {
                        stateSchoolSatScore = stateSchoolSatScore.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }

                    is Resource.Success -> {
                        result.data?.let { data ->
                            stateSchoolSatScore =
                                stateSchoolSatScore.copy(
                                    schoolDetails = data,
                                    isLoading = false,
                                    errorMessage = null
                                )
                        }
                    }
                    is Resource.Loading -> {
                        stateSchoolSatScore = stateSchoolSatScore.copy(isLoading = result.isLoading)
                    }
                }
            }

            //fetch school item from local database matching requested SchoolCode
            repository.getSchoolInfoByCode(schoolCode = schoolCode).collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        result.data?.let { data ->
                            stateSchoolSatScore =
                                stateSchoolSatScore.copy(
                                    schoolItem = data,
                                    errorMessage = null
                                )
                        }
                    }
                }

            }
        }
    }
}