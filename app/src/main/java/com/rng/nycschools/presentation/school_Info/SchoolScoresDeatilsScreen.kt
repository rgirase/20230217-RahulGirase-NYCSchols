package com.rng.nycschools.presentation.school_Info

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rng.nycschools.presentation.viewmodel.SchoolDetailViewModel

@Composable
fun SchoolScoresScreen(
    schoolId: String,
    viewModel: SchoolDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.stateSchoolSatScore
    if (state.schoolDetails != null) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
        ) {

            val schoolItem = state.schoolDetails
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(CenterHorizontally)
            ) {
                Text(text = schoolItem?.schoolName.toString())
                Spacer(modifier = Modifier.width(8.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SAT Reading Average Score: ")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = schoolItem?.readingAvgScore.toString())
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SAT Math Average Score: ")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = schoolItem?.mathAvgScore.toString())
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "SAT Writing Average Score: ")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = schoolItem?.writingAvgScore.toString())
            }
            Spacer(modifier = Modifier.height(4.dp))
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else {

        }

    }


}